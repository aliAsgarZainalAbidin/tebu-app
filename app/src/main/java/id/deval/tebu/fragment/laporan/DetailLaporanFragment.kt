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
import id.deval.tebu.utils.Constanta
import id.deval.tebu.viewmodels.LaporanViewModel
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import javax.inject.Inject


@AndroidEntryPoint
class DetailLaporanFragment : Fragment() {

    private val laporanViewModel: LaporanViewModel by viewModels()

    @Inject
    lateinit var session: Session
    private lateinit var _binding: FragmentDetailLaporanBinding
    private val binding get() = _binding
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
            laporanViewModel.getKebunBySinder(session.token!!, id.toString()).observe(viewLifecycleOwner){
                mtvLaporanNamaSinder.text = it[0].nama
                mtvLaporanWilayah.text = it[0].wilayah
                mtvLaporanLokasi.text = it[0].lokasi

                val detailAdapter = DetailLaporanAdapter(it, findNavController(), requireActivity())
                detailAdapter.notifyDataSetChanged()
                val lm = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                rvLaporanList.apply{
                    adapter = detailAdapter
                    layoutManager = lm
                }
            }
            val bitmapFactory = BitmapFactory.decodeResource(resources, R.drawable.ptpn)
            val logoBitmap = Bitmap.createScaledBitmap(bitmapFactory, 60, 60, false)

            val headers = arrayOf("No", "Username", "First Name", "Last Name")
            val rows = arrayOf(
                arrayOf("1", "jdow", "John", "Dow"),
                arrayOf("2", "stiger", "Scott", "Tiger"),
                arrayOf("3", "fbar", "Foo", "Bar")
            )

            val document = Document(PageSize.A4)
            try {
                val pdf = File("/sdcard/Download", "/TableDEMO.pdf")
                PdfWriter.getInstance(document, FileOutputStream(pdf))
                document.open()

                val fontHeader: Font = Font(Font.FontFamily.TIMES_ROMAN, 12f, Font.BOLD)
                val fontRow: Font = Font(Font.FontFamily.TIMES_ROMAN, 10f, Font.NORMAL)

                //Header Document
                val paragraph = Paragraph("Pabrik Gula Takalar")
                paragraph.alignment = Element.ALIGN_CENTER
                paragraph.font = fontHeader

                //Doc per Kebun


                val table = PdfPTable(headers.size)
                for (header in headers) {
                    val cell = PdfPCell()
                    cell.grayFill = 0.9f
                    cell.phrase = Phrase(header.toUpperCase(), fontHeader)
                    cell.horizontalAlignment = Element.ALIGN_CENTER
                    table.addCell(cell)
                }
                table.completeRow()
                for (row in rows) {
                    for (data in row) {
                        val phrase = Phrase(data, fontRow)
                        table.addCell(PdfPCell(phrase))
                    }
                    table.completeRow()
                }
                document.addTitle("PDF Table Demo")

                document.add(paragraph)
                document.add(table)
            } catch (e: DocumentException) {
                e.printStackTrace()
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } finally {
                document.close()
            }

            mbLaporanPdf.setOnClickListener {
                val document = PdfDocument()
                val pdf = PdfDocument.PageInfo.Builder(width, height, 1).create()
                val pdf1 = document.startPage(pdf)
                val canva = pdf1.canvas

                val paint = Paint()
                val titlePaint = Paint()
                titlePaint.textAlign = Paint.Align.CENTER
                titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD))
                titlePaint.textSize = 16f
                canva.drawText("Pabrik Gula Takalar", (width / 2).toFloat(), 32f, titlePaint)
                canva.drawBitmap(logoBitmap, 16f, 16f, paint)

                document.finishPage(pdf1)
                Log.d("TAG", "onViewCreatedsss: ${Environment.getExternalStorageDirectory().path}")
                val file = File("/sdcard/Download", "/Hello.pdf")
                try {
                    document.writeTo(FileOutputStream(file))
                } catch (e: Exception) {
                    Log.d("TAG", "onViewCreated: $e")
                }

                document.close()
            }
        }
    }
}