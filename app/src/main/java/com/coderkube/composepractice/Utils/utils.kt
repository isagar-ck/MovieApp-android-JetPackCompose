package com.coderkube.composepractice.Utils

import android.app.ProgressDialog
import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.text.TextUtils
import android.util.DisplayMetrics
import android.util.Patterns
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.coderkube.composepractice.R
import java.util.*

class utils {

    companion object {

        const val api_key = "c9856d0cb57c3f14bf75bdc6c063b8f3"
        private var dialog: ProgressDialog? = null

        /**
         * this function is use for Toast
         * */
        fun DebugToast(context: Context, message: String) {
            //if (BuildConfig.DEBUG) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            //}
        }


        /**
         * this function is use for Progress dialog
         * */
        fun ShowProgress(context: Context) {
            dialog = ProgressDialog(context, R.style.MyAlertDialogStyle);
            dialog!!.setMessage(context.getString(R.string.Please_wait));
            dialog!!.setCancelable(false)
            dialog!!.show();
        }

        fun HideProgress() {
            if (dialog!!.isShowing) {
                dialog!!.dismiss()
            }
        }

        /**
         * this function is use for check internet connection
         * */
        fun isConnect(context: Context): Boolean {
            var isConnected = false
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val capabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null) {
                    when {
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                            isConnected = true
                        }
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                            isConnected = true
                        }
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                            isConnected = true
                        }
                    }
                }
            } else {
                val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
                isConnected = activeNetwork?.isConnectedOrConnecting == true

            }
            if (!isConnected) {
                HideProgress()
                DebugToast(context, context.getString(R.string.check_internet))
            }
            return isConnected
        }


    }
}