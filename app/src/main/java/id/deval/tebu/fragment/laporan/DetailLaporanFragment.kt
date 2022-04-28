package id.deval.tebu.fragment.laporan

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.*
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.itextpdf.text.*
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
import id.deval.tebu.utils.HelperView
import id.deval.tebu.viewmodels.LaporanViewModel
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import javax.inject.Inject
import kotlin.math.roundToInt


@AndroidEntryPoint
class DetailLaporanFragment : Fragment() {

    private val laporanViewModel: LaporanViewModel by viewModels()

    @Inject
    lateinit var session: Session
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getString(Constanta.ID_ITEM_ARGS)

        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ),
            PackageManager.PERMISSION_GRANTED
        )


        with(binding) {
            listLaporan = ArrayList()
            laporanViewModel.getKebunBySinder(session.token!!, id.toString())
                .observe(viewLifecycleOwner) {
                    if (it.status == "failed"){
                        HelperView.showToast(it.message,requireContext()).show()
                    } else {
                        val data = it.data.laporan
                        if (data != null){
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
                                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                            rvLaporanList.apply {
                                adapter = detailAdapter
                                layoutManager = lm
                            }
                        }
                    }
                }
            val bitmapFactory = BitmapFactory.decodeResource(resources, R.drawable.ptpn)
            val logoBitmap = Bitmap.createScaledBitmap(bitmapFactory, 60, 60, false)

            mbLaporanPdf.setOnClickListener {
                val document = Document(PageSize.A4.rotate())
                try {
                    val pdf = File("/sdcard/Download", "/Sinder-${binding.mtvLaporanNamaSinder.text}.pdf")
                    PdfWriter.getInstance(document, FileOutputStream(pdf))
                    document.open()

                    val fontHeader: Font = Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.BOLD)
                    val fontRow: Font = Font(Font.FontFamily.TIMES_ROMAN, 9f, Font.NORMAL)

                    //Header Document
                    val paragraph = Paragraph("Pabrik Gula Takalar", fontHeader)
                    paragraph.alignment = Element.ALIGN_CENTER
                    paragraph.font = fontHeader
//                    val image = Image()

                    val table = PdfPTable(headers.size)
                    table.widthPercentage = 100f

                    for (header in headers) {
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
                        val kui = Phrase(data.kui.toDouble().roundToInt().toString(), fontRow)
                        table.addCell(PdfPCell(kui))
                        table.completeRow()
                    }
                    document.addTitle("PDF Table Demo")

                    //Doc per Kebun
                    val phraseSinder = Paragraph("Nama Sinder \t:${binding.mtvLaporanNamaSinder.text}")
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
                    document.close()
                }
            }
        }
    }
}