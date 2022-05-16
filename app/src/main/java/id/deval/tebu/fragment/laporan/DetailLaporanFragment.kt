package id.deval.tebu.fragment.laporan

import android.Manifest
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.itextpdf.text.*
import com.itextpdf.text.Font
import com.itextpdf.text.Font.FontFamily
import com.itextpdf.text.pdf.PdfPCell
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter
import dagger.hilt.android.AndroidEntryPoint
import id.deval.tebu.R
import id.deval.tebu.databinding.FragmentDetailLaporanBinding
import id.deval.tebu.db.Session
import id.deval.tebu.db.request.TaksasiWithUserRequest
import id.deval.tebu.utils.Constanta
import id.deval.tebu.utils.Constanta.headers
import id.deval.tebu.utils.Constanta.headersPdf
import id.deval.tebu.utils.Constanta.jmlhBatang
import id.deval.tebu.utils.Constanta.kuha
import id.deval.tebu.utils.Constanta.taksasi
import id.deval.tebu.utils.Constanta.tinggiBatang
import id.deval.tebu.utils.HelperView
import id.deval.tebu.viewmodels.LaporanViewModel
import org.apache.poi.hssf.util.HSSFColor
import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import javax.inject.Inject
import kotlin.math.nextUp
import kotlin.math.roundToInt


@AndroidEntryPoint
class DetailLaporanFragment : Fragment() {

    private val laporanViewModel: LaporanViewModel by viewModels()

    @Inject
    lateinit var session: Session
    private lateinit var workbook: Workbook
    private lateinit var _binding: FragmentDetailLaporanBinding
    private val binding get() = _binding
    private lateinit var listLaporan: ArrayList<TaksasiWithUserRequest>
    val width = 529
    val height = 824

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailLaporanBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getString(Constanta.ID_ITEM_ARGS)
        workbook = XSSFWorkbook()

        with(binding) {
            listLaporan = ArrayList()
            laporanViewModel.getKebunBySinder(session.token!!, id.toString())
                .observe(viewLifecycleOwner) {
                    if (it.status == "failed") {
                        HelperView.showToast(it.message, requireContext()).show()
                        disableCreateFile()
                    } else {
                        val data = it.data.laporan
                        if (data != null) {
                            listLaporan.addAll(data)
                            if (data.size > 0) {
                                mtvLaporanNamaSinder.text = data[0].nama
                                mtvLaporanWilayah.text = data[0].wilayah
                                mtvLaporanLokasi.text = data[0].lokasi
                            }

                            val detailAdapter =
                                DetailLaporanAdapter(data, findNavController(), requireActivity())
                            detailAdapter.notifyDataSetChanged()
                            val lm =
                                LinearLayoutManager(
                                    requireContext(),
                                    LinearLayoutManager.VERTICAL,
                                    false
                                )
                            rvLaporanList.apply {
                                adapter = detailAdapter
                                layoutManager = lm
                            }
                        }
                        disableCreateFile()
                    }
                }

            mbLaporanPdf.setOnClickListener {
                val document = Document(PageSize.A4.rotate())
                try {
                    val pdf =
                        File(
                            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                            "/Laporan Taksasi ${binding.mtvLaporanNamaSinder.text}.pdf"
                        )
                    PdfWriter.getInstance(document, FileOutputStream(pdf))
                    document.open()
                    Log.d(
                        TAG,
                        "onViewCreated: ${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)}"
                    )

                    val fontHeader: Font = Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD)
                    val fontRow: Font = Font(Font.FontFamily.TIMES_ROMAN, 9f, Font.NORMAL)

                    //Header Document
                    val paragraph = Paragraph("Pabrik Gula Takalar", fontHeader)
                    paragraph.alignment = Element.ALIGN_CENTER
                    paragraph.font = fontHeader

                    val table = PdfPTable(headersPdf.size)
                    table.widthPercentage = 100f

                    for (header in headersPdf) {
                        val cell = PdfPCell()
                        cell.grayFill = 0.9f
                        cell.phrase = Phrase(header, fontHeader)
                        cell.horizontalAlignment = Element.ALIGN_CENTER
                        table.addCell(cell)
                    }
                    table.completeRow()
                    for (data in listLaporan) {
                        val mandor = Phrase(data.mandor, fontRow)
                        table.addCell(PdfPCell(mandor))
                        val kebun = Phrase(data.namaKebun, fontRow)
                        table.addCell(PdfPCell(kebun))
                        val petak = Phrase(data.petak, fontRow)
                        table.addCell(PdfPCell(petak))
                        val luas = Phrase(data.luas, fontRow)
                        table.addCell(PdfPCell(luas))
                        val jenis = Phrase(data.jenis, fontRow)
                        table.addCell(PdfPCell(jenis))
                        val kategori = Phrase(data.kategori, fontRow)
                        table.addCell(PdfPCell(kategori))
                        val faktorLeng = Phrase(data.faktorLeng, fontRow)
                        table.addCell(PdfPCell(faktorLeng))
                        val batangPerMeter = Phrase(data.batangPerMeter, fontRow)
                        table.addCell(PdfPCell(batangPerMeter))
                        val batangPerRow = Phrase(data.batangPerRow, fontRow)
                        table.addCell(PdfPCell(batangPerRow))
                        val batangPerHa = Phrase(data.batangPerHa, fontRow)
                        table.addCell(PdfPCell(batangPerHa))
                        val tinggiIni = Phrase(data.tinggiIni, fontRow)
                        table.addCell(PdfPCell(tinggiIni))
                        val tinggiTebang = Phrase(data.tinggiTebang, fontRow)
                        table.addCell(PdfPCell(tinggiTebang))
                        val diameterBatang = Phrase(data.diameterBatang, fontRow)
                        table.addCell(PdfPCell(diameterBatang))
                        val beratPerMeter = Phrase(data.beratPerMeter, fontRow)
                        table.addCell(PdfPCell(beratPerMeter))
                        val hit = Phrase(data.hit, fontRow)
                        table.addCell(PdfPCell(hit))
                        val pandangan = Phrase(data.pandangan, fontRow)
                        table.addCell(PdfPCell(pandangan))
                        val perHit = Phrase(data.perHit, fontRow)
                        table.addCell(PdfPCell(perHit))
                        val kui = Phrase(data.kui?.toDouble()?.roundToInt().toString(), fontRow)
                        table.addCell(PdfPCell(kui))
                        table.completeRow()
                    }
                    document.addTitle("PDF Table Demo")

                    //Doc per Kebun
                    val phraseSinder =
                        Paragraph("Nama Sinder \t:${binding.mtvLaporanNamaSinder.text}")
                    val phraseWilayah = Paragraph("Wilayah \t\t:${binding.mtvLaporanWilayah.text}")
                    val phraseLokasi = Paragraph("Lokasi \t\t:${binding.mtvLaporanLokasi.text}")
                    val pharagrapSpace = Paragraph(" ")

                    document.add(paragraph)
                    document.add(phraseSinder)
                    document.add(phraseWilayah)
                    document.add(phraseLokasi)
                    document.add(pharagrapSpace)
                    document.add(table)
                } catch (e: DocumentException) {
                    e.printStackTrace()
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                } finally {
                    HelperView.showToast(
                        "Laporan Taksasi ${binding.mtvLaporanNamaSinder.text}.pdf was successfully created",
                        requireContext()
                    ).show()
                    document.close()
                }
            }

            mbLaporanExcel.setOnClickListener {
                val namaFile = mtvLaporanNamaSinder.text.toString()
                Log.d(TAG, "onViewCreated: CREATE NEW FILE")
                val sheet: Sheet
                val EXCEL_SHEET_NAME = "Sheet1"
                if (workbook.getSheet(EXCEL_SHEET_NAME) != null) {
                    workbook.removeSheetAt(0)
                }

                sheet = workbook.createSheet(EXCEL_SHEET_NAME)
                val fontHeader = workbook.createFont()
                fontHeader.bold = true
                sheet.addMergedRegion(CellRangeAddress(3, 4, 1, 1))
                sheet.addMergedRegion(CellRangeAddress(3, 4, 2, 2))
                sheet.addMergedRegion(CellRangeAddress(3, 4, 3, 3))
                sheet.addMergedRegion(CellRangeAddress(3, 4, 4, 4))
                sheet.addMergedRegion(CellRangeAddress(3, 4, 5, 5))
                sheet.addMergedRegion(CellRangeAddress(3, 4, 6, 6))
                sheet.addMergedRegion(CellRangeAddress(3, 3, 7, 10))
                sheet.addMergedRegion(CellRangeAddress(3, 3, 11, 12))
                sheet.addMergedRegion(CellRangeAddress(3, 4, 13, 13))
                sheet.addMergedRegion(CellRangeAddress(3, 4, 14, 14))
                sheet.addMergedRegion(CellRangeAddress(3, 3, 15, 16))
                sheet.addMergedRegion(CellRangeAddress(3, 3, 17, 18))
                var row = sheet.createRow(3)
                var cell: Cell
                val borderStyle = BorderStyle.MEDIUM
                var cellstyle = workbook.createCellStyle()
                cellstyle.fillForegroundColor = HSSFColor.AQUA.index
                cellstyle.fillBackgroundColor = HSSFColor.GREY_40_PERCENT.index
                cellstyle.setBorderTop(borderStyle)
                cellstyle.setBorderBottom(borderStyle)
                cellstyle.setFont(fontHeader)
                cellstyle.setBorderLeft(borderStyle)
                cellstyle.setBorderRight(borderStyle)
                cellstyle.setVerticalAlignment(VerticalAlignment.CENTER)
                cellstyle.setAlignment(HorizontalAlignment.CENTER)
                cellstyle.shrinkToFit = true

                var cellCommonStyle = workbook.createCellStyle()
                cellCommonStyle.setBorderTop(borderStyle)
                cellCommonStyle.setBorderBottom(borderStyle)
                cellCommonStyle.setFont(fontHeader)
                cellCommonStyle.setBorderLeft(borderStyle)
                cellCommonStyle.setBorderRight(borderStyle)

                val cellStyleExtraBorder = workbook.createCellStyle()
                cellStyleExtraBorder.setBorderBottom(BorderStyle.MEDIUM)
                cellStyleExtraBorder.setBorderLeft(BorderStyle.NONE)
                cellStyleExtraBorder.setBorderTop(BorderStyle.MEDIUM)
                cellStyleExtraBorder.setBorderRight(BorderStyle.MEDIUM)

                val cellStyleBorderBottom = workbook.createCellStyle()
                cellStyleBorderBottom.setBorderBottom(BorderStyle.MEDIUM)
                cellStyleBorderBottom.setBorderLeft(BorderStyle.MEDIUM)
                cellStyleBorderBottom.setBorderTop(BorderStyle.NONE)
                cellStyleBorderBottom.setBorderRight(BorderStyle.MEDIUM)

                headers.forEachIndexed { index, s ->
                    if (index < 7) {
                        cell = row.createCell(index + 1)
                    } else if (index == 7) {
                        for (x in index.plus(1)..index.plus(3)) {
                            cell = row.createCell(x)
                            cell.cellStyle = cellStyleExtraBorder
                        }
                        cell = row.createCell(index + 4)
                    } else if (index in 8..10) {
                        var cellExtra = row.createCell(12)
                        cellExtra.cellStyle = cellStyleExtraBorder
                        cellExtra = row.createCell(16)
                        cellExtra.cellStyle = cellStyleExtraBorder
                        cellExtra = row.createCell(18)
                        cellExtra.cellStyle = cellStyleExtraBorder
                        cell = row.createCell(index + 5)
                    } else {
                        cell = row.createCell(index + 6)
                    }
                    cell.setCellValue(s)
                    cell.cellStyle = cellstyle
                }

                //header sub jmlh Batang
                row = sheet.createRow(4)
                for (x in 1..6) {
                    cell = row.createCell(x)
                    cell.cellStyle = cellStyleBorderBottom
                }
                for (x in 13..14) {
                    cell = row.createCell(x)
                    cell.cellStyle = cellStyleBorderBottom
                }
                val startIndex = 7
                jmlhBatang.forEachIndexed { index, s ->
                    cell = row.createCell(startIndex.plus(index))
                    cell.setCellValue(s)
                    cell.cellStyle = cellstyle
                }
                //header sub tinggi batang
                val startIndexTinggi = 11
                tinggiBatang.forEachIndexed { index, s ->
                    cell = row.createCell(startIndexTinggi.plus(index))
                    cell.setCellValue(s)
                    cell.cellStyle = cellstyle
                }

                //header sub kuha
                val startIndexKuha = 15
                kuha.forEachIndexed { index, s ->
                    cell = row.createCell(startIndexKuha.plus(index))
                    cell.setCellValue(s)
                    cell.cellStyle = cellstyle

                }
                //header sub taksasi
                val startIndextaksasi = 17
                taksasi.forEachIndexed { index, s ->
                    cell = row.createCell(startIndextaksasi.plus(index))
                    cell.setCellValue(s)
                    cell.cellStyle = cellstyle
                }

                val cellItemStyle = workbook.createCellStyle()
                cellItemStyle.setBorderTop(borderStyle)
                cellItemStyle.setBorderBottom(borderStyle)
                cellItemStyle.setBorderLeft(borderStyle)
                cellItemStyle.setBorderRight(borderStyle)
                cellItemStyle.setVerticalAlignment(VerticalAlignment.CENTER)
                cellItemStyle.setAlignment(HorizontalAlignment.CENTER)

                listLaporan.forEachIndexed { index, taksasiWithUserRequest ->
                    row = sheet.createRow(index + 5)
                    var cellItem = row.createCell(1)
                    cellItem.cellStyle = cellItemStyle
                    cellItem.setCellValue(taksasiWithUserRequest.mandor)
                    cellItem = row.createCell(2)
                    cellItem.cellStyle = cellItemStyle
                    cellItem.setCellValue(taksasiWithUserRequest.namaKebun)
                    cellItem = row.createCell(3)
                    cellItem.cellStyle = cellItemStyle
                    cellItem.setCellValue(taksasiWithUserRequest.petak)
                    cellItem = row.createCell(4)
                    cellItem.cellStyle = cellItemStyle
                    cellItem.setCellValue(taksasiWithUserRequest.luas)
                    cellItem = row.createCell(5)
                    cellItem.cellStyle = cellItemStyle
                    cellItem.setCellValue(taksasiWithUserRequest.jenis)
                    cellItem = row.createCell(6)
                    cellItem.cellStyle = cellItemStyle
                    cellItem.setCellValue(taksasiWithUserRequest.kategori)
                    cellItem = row.createCell(7)
                    cellItem.cellStyle = cellItemStyle
                    cellItem.setCellValue(taksasiWithUserRequest.faktorLeng)
                    cellItem = row.createCell(8)
                    cellItem.cellStyle = cellItemStyle
                    cellItem.setCellValue(taksasiWithUserRequest.batangPerMeter)
                    cellItem = row.createCell(9)
                    cellItem.cellStyle = cellItemStyle
                    cellItem.setCellValue(taksasiWithUserRequest.batangPerRow)
                    cellItem = row.createCell(10)
                    cellItem.cellStyle = cellItemStyle
                    cellItem.setCellValue(taksasiWithUserRequest.batangPerHa)
                    cellItem = row.createCell(11)
                    cellItem.cellStyle = cellItemStyle
                    cellItem.setCellValue(taksasiWithUserRequest.tinggiIni)
                    cellItem = row.createCell(12)
                    cellItem.cellStyle = cellItemStyle
                    cellItem.setCellValue(taksasiWithUserRequest.tinggiTebang)
                    cellItem = row.createCell(13)
                    cellItem.cellStyle = cellItemStyle
                    cellItem.setCellValue(taksasiWithUserRequest.diameterBatang)
                    cellItem = row.createCell(14)
                    cellItem.cellStyle = cellItemStyle
                    cellItem.setCellValue(taksasiWithUserRequest.beratPerMeter)
                    cellItem = row.createCell(15)
                    cellItem.cellStyle = cellItemStyle
                    cellItem.setCellValue(taksasiWithUserRequest.hit)
                    cellItem = row.createCell(16)
                    cellItem.cellStyle = cellItemStyle
                    cellItem.setCellValue(taksasiWithUserRequest.pandangan)
                    cellItem = row.createCell(17)
                    cellItem.cellStyle = cellItemStyle
                    cellItem.setCellValue(taksasiWithUserRequest.perHit)
                    cellItem = row.createCell(18)
                    cellItem.cellStyle = cellItemStyle
                    cellItem.setCellValue(taksasiWithUserRequest.kui?.toDouble()?.nextUp() ?: 0.0)
                }

                val rowSinder = sheet.createRow(0)
                val cellSinder = rowSinder.createCell(1)
                cellSinder.setCellValue("Nama Sinder : ${listLaporan[0].nama}")
                val rowWilayah = sheet.createRow(1)
                val cellWilayah = rowWilayah.createCell(1)
                cellWilayah.setCellValue("Wilayah : ${listLaporan[0].wilayah}")
                val rowLokasi = sheet.createRow(2)
                val cellLokasi = rowLokasi.createCell(1)
                cellLokasi.setCellValue("Lokasi : ${listLaporan[0].lokasi}")

                val success = storeExcelInStorage("Laporan Taksasi $namaFile.xlsx")
                if (success) {
                    HelperView.showToast(
                        "Laporan Taksasi $namaFile.xlsx was successfully created",
                        requireContext()
                    ).show()
                }
            }
        }
    }

    private fun storeExcelInStorage(fileName: String): Boolean {
        var isSuccess: Boolean
        val file: File = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            fileName
        )
        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream = FileOutputStream(file)
            workbook.write(fileOutputStream)
            Log.e(TAG, "Writing file$file")
            isSuccess = true
        } catch (e: IOException) {
            Log.e(TAG, "Error writing Exception: ", e)
            isSuccess = false
        } catch (e: Exception) {
            Log.e(TAG, "Failed to save file due to Exception: ", e)
            isSuccess = false
        } finally {
            try {
                fileOutputStream?.close()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
        return isSuccess
    }

    private fun disableCreateFile() {
        with(binding) {
            if (listLaporan.size <= 0) {
                mbLaporanExcel.isEnabled = false
                mbLaporanPdf.isEnabled = false
            } else if (listLaporan[0].mandor.isNullOrEmpty() || listLaporan[0].kui.isNullOrEmpty() || listLaporan[0].perHit.isNullOrEmpty() || listLaporan[0].pandangan.isNullOrEmpty() || listLaporan[0].hit.isNullOrEmpty() || listLaporan[0].beratPerMeter.isNullOrEmpty() || listLaporan[0].diameterBatang.isNullOrEmpty() || listLaporan[0].tinggiTebang.isNullOrEmpty() || listLaporan[0].tinggiIni.isNullOrEmpty() || listLaporan[0].faktorLeng.isNullOrEmpty() || listLaporan[0].batangPerMeter.isNullOrEmpty() || listLaporan[0].batangPerRow.isNullOrEmpty() || listLaporan[0].batangPerHa.isNullOrEmpty()) {
                mbLaporanExcel.isEnabled = false
                mbLaporanPdf.isEnabled = false
                HelperView.showToast("Beberapa Data Belum diisi", requireContext()).show()
            } else {
                mbLaporanExcel.isEnabled = true
                mbLaporanPdf.isEnabled = true
            }
        }
    }

}