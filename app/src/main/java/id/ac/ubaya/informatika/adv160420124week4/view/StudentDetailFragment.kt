package id.ac.ubaya.informatika.adv160420124week4.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import id.ac.ubaya.informatika.adv160420124week4.R
import id.ac.ubaya.informatika.adv160420124week4.databinding.FragmentStudentDetailBinding
import id.ac.ubaya.informatika.adv160420124week4.util.loadImage
import id.ac.ubaya.informatika.adv160420124week4.util.showNotification
import id.ac.ubaya.informatika.adv160420124week4.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment(), ButtonUpdateClickListener, ButtonNotificationClickListener {
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var dataBinding:FragmentStudentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentStudentDetailBinding>(inflater, R.layout.fragment_student_detail, container, false)
        return dataBinding.root
    }

    fun observeViewModel(){
        detailViewModel.studentLD.observe(viewLifecycleOwner, Observer {
           dataBinding.student = it
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.notiflistener = this
        dataBinding.updatelistener = this

//        if(arguments != null) {
//            val idStudent = StudentDetailFragmentArgs.fromBundle(requireArguments()).idStudent
            detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            detailViewModel.fetch(StudentDetailFragmentArgs.fromBundle(requireArguments()).idStudent)
            observeViewModel()
//        }

//        val imgStudent = view.findViewById<ImageView>(R.id.imageView2)
//        val progBar = view.findViewById<ProgressBar>(R.id.progressBar2)
//        val txtID = view.findViewById<TextInputEditText>(R.id.txtID)
//        val txtName = view.findViewById<TextInputEditText>(R.id.txtName)
//        val txtBod = view.findViewById<TextInputEditText>(R.id.txtBod)
//        val txtPhone = view.findViewById<TextInputEditText>(R.id.txtPhone)
//        val btnNotif = view.findViewById<Button>(R.id.btnNotif)
//
//        detailViewModel.studentLD.observe(viewLifecycleOwner){studentDetail ->
//            imgStudent.loadImage(studentDetail.photoUrl,progBar)
//            txtID.setText(studentDetail.id.toString())
//            txtName.setText(studentDetail.name.toString())
//            txtBod.setText(studentDetail.bod.toString())
//            txtPhone.setText(studentDetail.phone.toString())
//
//            var student = studentDetail
//            btnNotif.setOnClickListener {
//                Observable.timer(5, TimeUnit.SECONDS)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe {
//                        Log.d("Messages", "five seconds")
//                        showNotification(student.name.toString(),
//                            "A new notification created",
//                            R.drawable.baseline_notifications_24)
//                    }
//            }
//        }
    }

    override fun onButtonUpdateClick(v: View) {

    }

    override fun onButtonNotificationClick(v: View) {
        Observable.timer(5, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("Messages", "five seconds")
                showNotification(dataBinding.student?.name.toString(),
                    "A new notification created",
                    R.drawable.baseline_notifications_24)
            }
    }
}