package id.ac.ubaya.informatika.adv160420124week4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import id.ac.ubaya.informatika.adv160420124week4.R
import id.ac.ubaya.informatika.adv160420124week4.util.loadImage
import id.ac.ubaya.informatika.adv160420124week4.viewmodel.DetailViewModel

class StudentDetailFragment : Fragment() {
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null) {
            val idStudent = StudentDetailFragmentArgs.fromBundle(requireArguments()).idStudent
            detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            detailViewModel.fetch(idStudent)
        }

        val imgStudent = view.findViewById<ImageView>(R.id.imageView2)
        val progBar = view.findViewById<ProgressBar>(R.id.progressBar2)
        val txtID = view.findViewById<TextInputEditText>(R.id.txtID)
        val txtName = view.findViewById<TextInputEditText>(R.id.txtName)
        val txtBod = view.findViewById<TextInputEditText>(R.id.txtBod)
        val txtPhone = view.findViewById<TextInputEditText>(R.id.txtPhone)

        detailViewModel.studentLD.observe(viewLifecycleOwner){studentDetail ->
            imgStudent.loadImage(studentDetail.photoUrl,progBar)
            txtID.setText(studentDetail.id.toString())
            txtName.setText(studentDetail.name.toString())
            txtBod.setText(studentDetail.bod.toString())
            txtPhone.setText(studentDetail.phone.toString())
        }
    }
}