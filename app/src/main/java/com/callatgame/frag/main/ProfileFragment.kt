package com.callatgame.frag.main

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.callatgame.frag.R
import com.callatgame.frag.common.ui.DataItemView
import com.callatgame.frag.core.AbstractFragment
import com.callatgame.frag.core.SessionManager
import com.squareup.picasso.Picasso


class ProfileFragment : AbstractFragment() {

    lateinit var profilPicImageView : ImageView
    lateinit var nicknameTextView : TextView

    lateinit var emailDiv : DataItemView
    lateinit var genderDiv : DataItemView
    lateinit var lastNameDiv : DataItemView
    lateinit var firstNameDiv : DataItemView
    lateinit var dobDiv : DataItemView

    lateinit var sizeDiv : DataItemView
    lateinit var weightDiv : DataItemView
    lateinit var attackDiv : DataItemView
    lateinit var defenseDiv : DataItemView
    lateinit var speedDiv : DataItemView
    lateinit var staminaDiv : DataItemView
    lateinit var aimDiv : DataItemView
    lateinit var technicDiv : DataItemView







    companion object{

        fun newInstance() : Fragment {
            return ProfileFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.profile_fragment, container, false)

        profilPicImageView = view.findViewById(R.id.profile_pic_imageview)
        nicknameTextView = view.findViewById(R.id.nickname_textview)

        emailDiv = view.findViewById(R.id.email_div)
        genderDiv = view.findViewById(R.id.gender_div)
        lastNameDiv = view.findViewById(R.id.lastname_div)
        firstNameDiv = view.findViewById(R.id.firstname_div)
        dobDiv = view.findViewById(R.id.dob_div)

        sizeDiv = view.findViewById(R.id.size_div)
        weightDiv = view.findViewById(R.id.weight_div)
        attackDiv = view.findViewById(R.id.attack_div)
        defenseDiv = view.findViewById(R.id.defense_div)
        speedDiv = view.findViewById(R.id.speed_div)
        staminaDiv = view.findViewById(R.id.stamina_div)
        aimDiv = view.findViewById(R.id.aim_div)
        technicDiv = view.findViewById(R.id.technic_div)

        setUpDataItemView()
        fillWithUserData()

        return view
    }

    private fun setUpDataItemView() {


    }

    private fun fillWithUserData() {

        Picasso.get()
            .load(SessionManager.instance.user.profilPic)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(profilPicImageView);

        nicknameTextView.text = SessionManager.instance.user.userName

        emailDiv.setValue(SessionManager.instance.user.email)
        genderDiv.setValue(SessionManager.instance.user.gender)
        lastNameDiv.setValue(SessionManager.instance.user.lastName)
        firstNameDiv.setValue(SessionManager.instance.user.firstName)
        dobDiv.setValue(SessionManager.instance.user.dateOfBirth)

        sizeDiv.setValue("- m")
        weightDiv.setValue("- kg")
        attackDiv.setValue("-")
        defenseDiv.setValue("-")
        speedDiv.setValue("-")
        staminaDiv.setValue("-")
        aimDiv.setValue("-")
        technicDiv.setValue("-")

    }
}