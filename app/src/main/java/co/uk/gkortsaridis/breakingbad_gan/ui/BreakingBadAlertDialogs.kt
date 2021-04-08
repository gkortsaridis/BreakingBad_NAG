package co.uk.gkortsaridis.breakingbad_gan.ui

import android.app.Activity
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AlertDialog
import co.uk.gkortsaridis.breakingbad_gan.R
import co.uk.gkortsaridis.breakingbad_gan.interfaces.SeasonsFilterListener
import kotlinx.android.synthetic.main.alert_dialog_filter_seasons.view.*

object BreakingBadAlertDialogs {

    fun seasonFilterAlertDialog(activity: Activity,seasons: ArrayList<Int>, listener: SeasonsFilterListener?): AlertDialog {

        val customLayout: View = activity.layoutInflater.inflate(R.layout.alert_dialog_filter_seasons, null)
        var dialog: AlertDialog? = null
        val builder = AlertDialog.Builder(activity)

        customLayout.s1sw.isChecked = seasons.contains(1)
        customLayout.s2sw.isChecked = seasons.contains(2)
        customLayout.s3sw.isChecked = seasons.contains(3)
        customLayout.s4sw.isChecked = seasons.contains(4)
        customLayout.s5sw.isChecked = seasons.contains(5)

        customLayout.apply_btn.setOnClickListener {
            val seasons = ArrayList<Int>()
            if(customLayout.s1sw.isChecked) seasons.add(1)
            if(customLayout.s2sw.isChecked) seasons.add(2)
            if(customLayout.s3sw.isChecked) seasons.add(3)
            if(customLayout.s4sw.isChecked) seasons.add(4)
            if(customLayout.s5sw.isChecked) seasons.add(5)

            listener?.filterApplied(seasons)
            dialog?.dismiss()
        }


        builder.setView(customLayout)
        dialog = builder.create()
        dialog.setCancelable(true)
        dialog.window?.setBackgroundDrawableResource(R.color.transparent)

        return dialog
    }

}