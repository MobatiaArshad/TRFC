package com.a71cities.trfc.utils


import android.Manifest
import android.R.attr
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.Settings
import android.text.*
import android.text.style.StyleSpan
import android.util.Log
import android.util.Patterns
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.JsonObject
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import kotlin.reflect.KFunction0
import android.widget.TimePicker

import android.app.TimePickerDialog.OnTimeSetListener
import android.graphics.*
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Environment
import java.io.FileOutputStream
import java.io.InputStream
import java.sql.Time
import kotlin.concurrent.thread
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import androidx.core.app.ActivityCompat.startActivityForResult

import android.R.attr.path
import android.annotation.SuppressLint
import android.app.*
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager

import android.provider.DocumentsContract

import androidx.core.content.ContextCompat.startActivity

import android.os.Build
import android.os.Handler
import android.webkit.MimeTypeMap
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.a71cities.trfc.R
import com.a71cities.trfc.utils.commonModel.CommonResponse
import com.google.gson.Gson
import org.json.JSONObject
import java.io.IOException
import java.time.LocalTime


const val GALLERY_IMAGE_REQ_CODE = 102
const val CAMERA_IMAGE_REQ_CODE = 103

fun TabLayout.onTabSelect(function: (selectedTabPosition: Int) -> Unit) {
    addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            function?.invoke(selectedTabPosition)
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {

        }

        override fun onTabReselected(tab: TabLayout.Tab?) {

        }

    })

}


fun Array<out View>.onclickSingleSelection(
    defaultSelected: View? = null,
    invokeCallbackOnDefaultSelection: Boolean = true,
    callBack: ((selecte_view: View) -> Unit)? = null,

    ) {

    fun select(v: View, invokeCallback: Boolean = true) {
        forEach {
            it.isSelected = it == v
            if (it.isSelected && invokeCallback) {
                callBack?.invoke(v)
            }
            println("invokeCallback $invokeCallback $v")
        }
    }
    forEach {
        it.setOnClickListener { select(it, true) }
    }
    if (defaultSelected != null)
        select(defaultSelected, invokeCallbackOnDefaultSelection)


}


fun Array<out CompoundButton>.onSingleCheck(
    defaultSelected: CompoundButton? = null,
    invokeCallbackOnDefaultSelection: Boolean = true,
    callBack: ((selecte_view: CompoundButton) -> Unit)? = null,

    ) {

    fun select(v: CompoundButton, invokeCallback: Boolean = true) {
        forEach {
            it.isChecked = it == v
            if (invokeCallback) {
                callBack?.invoke(v)
            }
            println("invokeCallback $invokeCallback $v")
        }
    }
    forEach {
        it.setOnClickListener {_-> select(it, true) }
    }
    if (defaultSelected != null)
        select(defaultSelected, invokeCallbackOnDefaultSelection)


}
















fun Array<out View>.select1(
    defaultSelected: View
) {
    forEach {
        it.isSelected = it == defaultSelected

    }
}


/*fun ImageView?.fullScreen(list: Collection<String?>, position: Int = 0) {
    if (this != null) {
        setOnClickListener {
            StfalconImageViewer.Builder<String>(context, list.toList()) { view, image ->
                view.load(image)
            }
                .withStartPosition(position)
                .show()
        }

    }

}*/


fun convertDate(s: String?, fIn: String, fOut: String): String {
    return try {
        SimpleDateFormat(fOut, Locale.ENGLISH).format(
            SimpleDateFormat(fIn, Locale.ENGLISH).parse(s)
        )
    } catch (e: Exception) {
        s ?: ""
    }
}


fun TextView?.isEllipsized(): Boolean {


    Log.d("isEllipsizedd", "${this?.lineCount}")
    this?.let { textview1 ->
        val layout = textview1.getLayout();
        if (layout != null) {
            val lines = layout.getLineCount();
            if (lines > 0) {
                val ellipsisCount = layout.getEllipsisCount(lines - 1);
                if (ellipsisCount > 0) {
                    Log.d("TAG", "Text is ellipsized");
                    return true
                }
            }
        }
    }
    return false
}


internal fun EditText.setEditable(isEditable: Boolean) {
    isEnabled = isEditable
    isClickable = isEditable
    isFocusableInTouchMode = isEditable
    isFocusable = isEditable
}

internal fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

internal fun ViewGroup.addView(@LayoutRes layoutRes: Int): View {
    val v = LayoutInflater.from(context).inflate(layoutRes, this, false)
    addView(v)
    return v
}


val TextView?.isTextEmpty: Boolean
    get() {
        return this?.text?.toString()?.trim()?.isEmpty() ?: true
    }


val TextView?.isValidEmail: Boolean
    get() {
        return Patterns.EMAIL_ADDRESS.matcher(this?.text?.toString()).matches()
    }
//
//val CharSequence?.isValidEmail: Boolean
//    get() {
//        return Patterns.EMAIL_ADDRESS.matcher(this!!).matches()
//    }

fun isValidEmail(input: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(input).matches()



val TextView?.isValidPhone: Boolean
    get() {
        return Patterns.PHONE.matcher(this?.text?.toString()).matches()
    }


val TextView?.hasText: Boolean
    get() {
        return this?.text?.toString()?.isNotBlank() ?: false
    }


fun View?.fixHeight() {
    this?.post {
        this?.layoutParams?.height = this.height
    }
}


/*fun ImageView?.loadUrl(url: String?, callback: Callback? = null, errorHolder: Int? = null) {
    if (this == null) return
    if ((url.isNullOrBlank() && errorHolder != null) || (getTag(R.string.image_error) == true && errorHolder != null)) {
        setImageResource(errorHolder)
    } else {
        val picas = Picasso.get().load(BASE_URL_IMAGE + url).noFade()

        picas.into(this, object : Callback {
            override fun onError(e: java.lang.Exception?) {
                setTag(R.string.isDissolvEffectDone, true)
                callback?.onError(e)
                setTag(R.string.image_error, true)
                if (errorHolder != null) setImageResource(errorHolder)
            }

            override fun onSuccess() {
                if (getTag(R.string.isDissolvEffectDone) != true) {
                    val fadeOut = AlphaAnimation(0f, 1f)
                    fadeOut.interpolator = AccelerateInterpolator()
                    fadeOut.duration = 750
                    startAnimation(fadeOut)
                }

                setTag(R.string.isDissolvEffectDone, true)
                callback?.onSuccess()
            }

        })
    }


}*/


/*
fun ImageView?.loadProfileImage(url: String?, callback: Callback? = null) {
    if (this == null) return
*/
/*    if (url == null || url.trim().isBlank() || this.getTag(R.string.image_error) == true) {
        *//*
*/
/* IMAGE Error*//*
*/
/*
        this.setTag(R.string.image_error, true)
        Picasso.get().load(BASE_URL_IMAGE + url).*//*
*/
/*placeholder(R.drawable.avatar).error_red(R.drawable.avatar).*//*
*/
/*into(this, object : Callback {
            override fun onSuccess() {
                if (getTag(R.string.imageLoaded) != true) {
                    setAlpha(0f)
                    animate().setDuration(250).alpha(1f).start();
                }

                setTag(R.string.imageLoaded, true)
                callback?.onSuccess()
            }

            override fun onError(e: java.lang.Exception?) {
                setTag(R.string.imageLoaded, true)
                callback?.onError(e)
                this@loadProfileImage.setTag(R.string.image_error, true)
            }

        })

    } else {
        Picasso.get().load(BASE_URL_IMAGE + url)*//*
*/
/*.error_red(R.drawable.avatar)*//*
*/
/*.into(this, object : Callback {
            override fun onSuccess() {
                if (getTag(R.string.imageLoaded) != true) {
                    setAlpha(0f)
                    animate().setDuration(250).alpha(1f).start();
                }

                setTag(R.string.imageLoaded, true)
                callback?.onSuccess()
            }

            override fun onError(e: java.lang.Exception?) {
                setTag(R.string.imageLoaded, true)
                callback?.onError(e)
                this@loadProfileImage.setTag(R.string.image_error, true)
            }

        })
    }*//*




    Picasso.get().load(BASE_URL_IMAGE + url).noFade()*/
/*.error_red(R.drawable.avatar)*//*
.into(this, object : Callback {
        override fun onSuccess() {
            if (getTag(R.string.isDissolvEffectDone) != true) {
                val fadeOut = AlphaAnimation(0f, 1f)
                fadeOut.interpolator = AccelerateInterpolator()
                fadeOut.duration = 750
                startAnimation(fadeOut)
            }

            setTag(R.string.isDissolvEffectDone, true)
            callback?.onSuccess()
        }

        override fun onError(e: java.lang.Exception?) {
            setTag(R.string.isDissolvEffectDone, true)
            callback?.onError(e)
            setTag(R.string.image_error, true)
        }

    })
}
*/


fun JsonObject.addProperty(s: String, editText: TextView?) {
    addProperty(s, editText?.text?.toString()?.trim())

}


fun isSameDay(cal1: Calendar?, cal2: Calendar?): Boolean {
    return cal1 != null &&
            cal2 != null &&
            cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
            cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
            cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE)
}


fun Calendar.isTodayOrTomeorrow(): Boolean {
    return this != null && (isSameDay(this, Calendar.getInstance()) ||
            isSameDay(this, Calendar.getInstance().apply { add(Calendar.DATE, 1) }))
}


fun Calendar.isTodayOrTomeorrowOrDayAfterTomorrow(): Boolean {
    return this != null && (
            isSameDay(this, Calendar.getInstance())
                    ||
                    isSameDay(this, Calendar.getInstance().apply { add(Calendar.DATE, 1) })
                    ||
                    isSameDay(this, Calendar.getInstance().apply { add(Calendar.DATE, 2) })
            )
}


fun String?.convertApiDateToDisplayDate(): String {
    return if (this == null || this.isBlank() || this == "0000-00-00") {
        ""

    } else {
        try {

            SimpleDateFormat("d-MM-yyyy", Locale.ENGLISH).format(
                SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(
                    this
                )
            )
        } catch (e: Exception) {
            ""
        }
    }
}

fun String?.convertApiDateToCalendar(): Calendar? {
    return if (this == null || this.isBlank() || this == "0000-00-00") {
        null

    } else {
        try {

            Calendar.getInstance().apply {
                timeInMillis = SimpleDateFormat(
                    "yyyy-MM-dd",
                    Locale.ENGLISH
                ).parse(this@convertApiDateToCalendar).time
            }
        } catch (e: Exception) {
            null
        }
    }
}


fun String?.convertDisplayDateToApiDate(): String {
    return if (this == null || this.isBlank() || this == "0000-00-00") {
        ""

    } else {
        try {

            SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(
                SimpleDateFormat("d-MM-yyyy", Locale.ENGLISH).parse(
                    this
                )
            )
        } catch (e: Exception) {
            ""
        }
    }
}


fun Calendar?.convertCaledarToApiDate(): String {
    return if (this == null || this.timeInMillis < 1000) {
        ""

    } else {
        try {
            SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(this.time)
        } catch (e: Exception) {
            ""
        }
    }
}

fun TextInputLayout.markRequired() {
    hint = "$hint *"
}


fun EditText?.onDone(
    kFunctionOnSearch: KFunction0<Unit>,
    reset: View? = null,
    kFunctionReset: KFunction0<Unit>? = null
) {
    this?.setOnFocusChangeListener { v, hasFocus ->
        if (hasFocus)
            reset?.visibility = View.VISIBLE
        else
            reset?.visibility = View.GONE

    }

    this?.setOnEditorActionListener { v, actionId, event ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH
            || actionId == EditorInfo.IME_ACTION_DONE ||
            actionId == EditorInfo.IME_ACTION_NEXT
            || event != null && event.getAction() == KeyEvent.ACTION_DOWN
            && event.getKeyCode() == KeyEvent.KEYCODE_ENTER
        ) {
            /*   this.hideKeyboardView()*/
            kFunctionOnSearch()
            //  this@onDone.clearFocus()

            return@setOnEditorActionListener true
        }
        return@setOnEditorActionListener false
    }

    reset?.setOnClickListener {
        if (kFunctionReset != null) {
            kFunctionReset()
        }
    }
}


public interface OnDone {
    public fun onDone()
}


fun EditText?.onDone(l: (View) -> Unit) {
    this?.setOnEditorActionListener { v, actionId, event ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH
            || actionId == EditorInfo.IME_ACTION_DONE ||
            actionId == EditorInfo.IME_ACTION_NEXT
            || event != null && event.getAction() == KeyEvent.ACTION_DOWN
            && event.getKeyCode() == KeyEvent.KEYCODE_ENTER
        ) {
            /*   this.hideKeyboardView()*/
            l.invoke(this)
            //  this@onDone.clearFocus()

            return@setOnEditorActionListener true
        }
        return@setOnEditorActionListener false
    }

}

fun AutoCompleteTextView?.hideErrorOnSelection() {
    this?.addTextChangedListener {
        if (text.isNotBlank())
            if (error != null)
                setError(null)
    }

}

fun String?.floatStringIn2DecimalIfAny(): String {
    if (this?.isNullOrBlank() == true) return ""
    else if (!contains(".")) return this else if (toFloatOrNull() != null) return String.format(
        Locale.ENGLISH,
        "%.2f",
        toFloat()
    ) else return this
}


fun View?.hideKeyboardView() {
    if (this == null || this.context == null) return
    val inputMethodManager =
        this.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

    if (inputMethodManager != null && inputMethodManager.isActive) {
        //inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        //InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
    }

}

fun Activity?.hideKeyboardView() {
    if (this != null && this.window != null && this.window.decorView != null) {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this.window.decorView.windowToken, 0)
    }
}


fun Fragment?.hideKeyboardView() {
    this?.activity?.window?.decorView?.let {
        val imm =
            this.requireActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this.requireActivity().window.decorView.windowToken, 0)
    }


}


fun Fragment?.showKeyboard(editText: EditText) {
    this?.activity?.window?.decorView?.let {
        val imm =
            this.requireActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, 0)
    }


}


fun EditText.bold() {

    this.setText(SpannableString(this.text.toString()).apply {
        setSpan(StyleSpan(Typeface.BOLD), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    })


}


fun Context?.getDeviceID(): String? {
    return Settings.Secure.getString(this?.contentResolver, Settings.Secure.ANDROID_ID)
}


fun Fragment?.getDeviceID(): String? {
    return Settings.Secure.getString(this?.activity?.contentResolver, Settings.Secure.ANDROID_ID)
}

val Calendar.isLessThanOrEqual48HoursFromNow: Boolean
    get() {
        return timeInMillis - Calendar.getInstance().timeInMillis <= TimeUnit.HOURS.toMillis(48)
    }


val Calendar.isLessThanOrEqual24HoursFromNow: Boolean
    get() {
        return timeInMillis - Calendar.getInstance().timeInMillis <= TimeUnit.HOURS.toMillis(24)
    }


/*val JsonObject.toMultiPart: HashMap<String, RequestBody>
    get() {
        val partRequest = HashMap<String, RequestBody>()
        keySet().forEach {
            partRequest[it] = Utils.toRequestBody(this.get(it).asString)
        }
        return partRequest
    }*/


public fun Intent.putExtra(s: String, textView: TextView) {
    putExtra(s, textView.text.toString())
}


public fun Bundle.putString(s: String, textView: TextView) {
    putString(s, textView.text.toString())
}


@Throws(Exception::class)
fun JsonObject?.isStatusSuccess(): Boolean {
    return this?.get("status")?.asString == "200"
}


val RTL_EMBED = "\u202B"
val LTR_EMBED = "\u202A"

public fun String.ltrEmbed(): String {
    return LTR_EMBED + this + LTR_EMBED
}

/*
public fun String?.withCurrency(
        context: Context? = AppApplication.instance,
        symbol_left: String? = null,
        symbol_right: String? = null,
       *//* scale: Int? = null,*//*


        ): String {
    try {
        if (this.isNullOrBlank()) return ""
        Log.d("embedPrice", "embedPrice: $this")
        val country = SharedPreferanceUtils.getCountry(context)
        val bd = this?.toBigDecimal()
                ?.setScale(
                      *//*  if (scale != null) scale else*//*
                            if (country?.iso_code?.toLowerCase() == "kw") 3
                            else 2, BigDecimal.ROUND_HALF_UP)


        val symbolRight = symbol_right ?: country?.symbol_right ?: "".trim()
        val symbolLeft = symbol_left ?: country?.symbol_left ?: "".trim()
        Log.d("withCurrency", "withCurrency: $bd")
        val s = LTR_EMBED + (symbolLeft + " " + bd.toString().replaceAR() + " " + symbolRight).trim() + LTR_EMBED
        Log.d("withCurrency", "withCurrency: $bd $s")
        return s
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return ""
}*/

val ars = arrayOf(
    "١",
    "٢",
    "٣",
    "٤",
    "٥",
    "٦",
    "7",
    "٨",
    "٩",
    "٠",
    "۱",
    "۲",
    "۳",
    "۴",
    "۵",
    "۶",
    "۷",
    "۸",
    "۹",
    "۰"
)


fun String?.replaceAR(): String {
    return this ?: ""
        .replace("١", "1")
        .replace("٢", "2")
        .replace("٣", "3")
        .replace("٤", "4")
        .replace("٥", "5")
        .replace("٦", "6")
        .replace("٧", "7")
        .replace("٨", "8")
        .replace("٩", "9")
        .replace("٠", "0")
        .replace("۱", "1")
        .replace("۲", "2")
        .replace("۳", "3")
        .replace("۴", "4")
        .replace("۵", "5")
        .replace("۶", "6")
        .replace("۷", "7")
        .replace("۸", "8")
        .replace("۹", "9")
        .replace("۰", "0")
}


public fun Activity?.isTrue(string: String): Boolean {
    return this?.intent?.getBooleanExtra(string, false) == true
}


public fun Intent?.isTrue(string: String): Boolean {
    return this?.getBooleanExtra(string, false) == true
}


public fun Bundle?.isTrue(string: String): Boolean {
    return this?.getBoolean(string, false) == true
}


public fun Fragment?.isTrue(string: String): Boolean {
    return this?.arguments?.getBoolean(string, false) == true
}


fun Context.getResearchStorageDir(): File {
    return File(filesDir, "docs").apply {
        if (!exists())
            mkdir()
        else if (!isDirectory) {
            delete()
            mkdir()
        }


    }


}


fun String?.toDisplayDate(): String {
    if (this.isNullOrBlank()) return ""
    return try {
        val parsed = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(this)
        SimpleDateFormat("d-M-yyyy", Locale.ENGLISH).format(parsed.time)
    } catch (e: Exception) {
        this
    }
}


//fun JsonObject?.getAPIResponseMessage(): String {
//    var message: String? = null
//    if (this != null) {
//        try {
//            if (Pref.isArabic) {
//                if (this.has("message_arabic"))
//                    message = this.get("message_arabic").asString
//                else if (this.has("message_ar"))
//                    message = this.get("message_ar").asString
//                else if (this.has("message"))
//                    message = this.get("message").asString
//
//            } else {
//                if (this.has("message"))
//                    message = this.get("message").asString
//                else if (this.has("message_ar"))
//                    message = this.get("message_ar").asString
//                else if (this.has("message_arabic"))
//                    message = this.get("message_arabic").asString
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//
//    }
//    return if (message == null) "" else message
//}


fun View.setOnClickListenerDummy(listener: (View) -> Unit) {
    listener(this)
}


fun TextView.bold() {
    this.text = this.text.toString().bold()
}

/*
fun TextView.calligraphyFont(@StringRes strFontId: Int) {
    setText(SpannableString(text.toString()).apply {
        setSpan(CalligraphyTypefaceSpan(TypefaceUtils.load(context?.assets, context.getString(strFontId))), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    })
}
*/


fun TextView.boldClear() {
    this.text = this.text.toString().boldClear()
}

fun String?.bold(): SpannableString {
    return SpannableString(this).apply {
        setSpan(StyleSpan(Typeface.BOLD), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
}

fun String?.boldClear(): SpannableString {
    return SpannableString(this).apply {
        setSpan(StyleSpan(Typeface.NORMAL), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
}


/*fun logEventsToFireBase(key: String, vale: String) {
    Log.d(key, vale)
    try {
        FirebaseAnalytics.getInstance(AppApplication.instance).logEvent("LOGS", Bundle().apply {
            putString(key, vale)

        })
    } catch (e: Exception) {
    }
}*/

/*fun Throwable?.printAndReportException() {
    if (this != null) {
        printStackTrace()
        FirebaseCrashlytics.getInstance().recordException(this)
    } else {
        Log.d("logExceptionAndReport", "Exception null")
    }
}*/


fun Activity?.getColorFromResource(@ColorRes colorResource: Int): Int {
    if (this != null) {
        return ContextCompat.getColor(this, colorResource)
    } else {
        return Color.TRANSPARENT
    }
}


fun Fragment?.getColorFromResource(@ColorRes colorResource: Int): Int {
    return if (this?.activity != null) {
        ContextCompat.getColor(this.requireActivity(), colorResource)
    } else {
        Color.TRANSPARENT
    }
}

fun View?.getColorFromResource(@ColorRes colorResource: Int): Int {
    if (this?.context != null) {
        return ContextCompat.getColor(this?.context!!, colorResource)
    } else {
        return Color.TRANSPARENT
    }
}


fun pxFromDp(context: Context, dp: Float): Float {
    return dp * context.resources.displayMetrics.density
}


fun dpFromPx(context: Context, px: Float): Float {
    return px / context.resources.displayMetrics.density
}


fun CharSequence?.trimTrailingWhitespace(): CharSequence? {
    if (this == null) return ""
    var i = this.length

    // loop back to the first non-whitespace character
    while (--i >= 0 && Character.isWhitespace(this[i])) {
    }
    return this.subSequence(0, i + 1)
}


/*fun getShortDayNames(int: Int): String {
    if (AppApplication.isArabic) {
        return dayNamesAr[calDays.indexOf(int)]
    } else {
        return dayNamesEnShort[calDays.indexOf(int)]
    }
}*/

/*fun getFullDayNames(int: Int): String {
    if (AppApplication.isArabic) {
        return dayNamesAr[calDays.indexOf(int)]
    } else {
        return dayNamesEnFull[calDays.indexOf(int)]
    }
}*/


val calDays = arrayOf(
    Calendar.SUNDAY,
    Calendar.MONDAY,
    Calendar.TUESDAY,
    Calendar.WEDNESDAY,
    Calendar.THURSDAY,
    Calendar.FRIDAY,
    Calendar.SATURDAY
)
val dayNamesEnFull = arrayOf(
    "Sunday",
    "Monday",
    "Tuesday",
    "Wednesday",
    "Thursday",
    "Friday",
    "Saturday"
)


val dayNamesEnShort = arrayOf(
    "Sun",
    "Mon",
    "Tue",
    "Wed",
    "Thu",
    "Fri",
    "Sat"
)


val dayNamesAr = arrayOf(
    "الأحد",
    "الإثنين",
    "الثلاثاء",
    "الأربعاء",
    "الخميس",
    "الجمعة",
    "السبت"
)


fun ImageView.fetch(url: String?, @DrawableRes error: Int = R.mipmap.app_icon, @DrawableRes placeholder: Int = R.mipmap.app_icon) {
    Glide
        .with(context)
        .load(url)
        .centerCrop()
        .placeholder(placeholder)
        .error(error) // todo remove
        .into(this)
}

fun NestedScrollView.reachedBottom(l: (View) -> Unit){
    this.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
        if (scrollY == v?.getChildAt(0)!!.measuredHeight - v.measuredHeight){
            l.invoke(this@reachedBottom)
        }
    })
}

fun NestedScrollView.paginate(l: (View) -> Unit){
    this.viewTreeObserver.addOnScrollChangedListener {
        val view: View = this@paginate.getChildAt(this@paginate.childCount - 1)
        val diff = (view.bottom - (this@paginate.height + this@paginate.scrollY))

        if (diff == 0){
            l.invoke(this)
        }

    }
}

fun RecyclerView.runWhenReady(action: () -> Unit) {
    val globalLayoutListener = object: ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            action()
            viewTreeObserver.removeOnGlobalLayoutListener(this)
        }
    }
    viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
}

fun TextView.leftDrawable(@DrawableRes id: Int = 0) {
    this.setCompoundDrawablesWithIntrinsicBounds(id, 0, 0, 0)
}

/*fun ImageView?.picasoLoadImageSp1(image: String?, placeHolder: String = AppConfig.place_holder_no_image) {
    Log.d("PICASO", "picasoLoadImageSp1: " + AppConfig.BASE_URL_IMAGE + image)
    this?.let { imageView ->
        Utils.log("LLLJJJJ")
        val tag = System.currentTimeMillis()
        imageView.setTag(tag)
        try {
            Utils.log("LLLJJJJ")
            if (image?.trim().isNullOrBlank() || placeHolder == AppConfig.BASE_URL_IMAGE + image) {
                imageView?.picasoLoadImageSp1LoadPlaceHolder(image, placeHolder, tag)
                Utils.log("LLLJJJJ")
            } else {
                Utils.log("LLLJJJJ")
                Picasso.get().load(AppConfig.BASE_URL_IMAGE + image).into(imageView, object : Callback {
                    override fun onSuccess() {
                        Utils.log("LLLJJJJ")
                    }

                    override fun onError(e: java.lang.Exception?) {
                        Utils.log("LLLJJJJ")
                        if (tag == imageView.tag) {
                            Utils.log("LLLJJJJ")
                            imageView?.picasoLoadImageSp1LoadPlaceHolder(image, placeHolder, tag)
                        }
                    }

                })
            }
        } catch (e: Exception) {
            Utils.log("LLLJJJJ")
            e.printStackTrace()
        }
    }

}*/


/*private fun ImageView?.picasoLoadImageSp1LoadPlaceHolder(image: String?, placeHolder: String, tag: Long) {
    this?.let { imageView ->
        try {
            Utils.log("LLLJJJJ")
            if (tag == imageView.tag) {
                Utils.log("LLLJJJJ")
                Picasso.get().load(placeHolder).into(imageView, object : Callback {
                    override fun onSuccess() {
                        Utils.log("LLLJJJJ")
                        GlobalScope.launch {
                            Utils.log("LLLJJJJ")
                            val clp = pref.getLong("cl_$placeHolder", 0L)
                            var cl = AppApplication.rxRestService.loadUrlSuspended(placeHolder).contentLength()
                            Log.d("hghghghggh", "$tag $clp $cl")
                            if (cl > 0 && clp != cl) {
                                Utils.log("LLLJJJJ")
                                editPref.putLong("cl_$placeHolder", cl).commit()
                                Picasso.get().invalidate(placeHolder)
                                picasoLoadImageSp1LoadPlaceHolder(image, placeHolder, tag)
                            }

                        }

                    }

                    override fun onError(e: java.lang.Exception?) {
                        Utils.log("LLLJJJJ")
                    }

                })
            }
        } catch (e: Exception) {
            Utils.log("LLLJJJJ")
            e.printStackTrace()
        }

    }

}*/


/*fun ImageView?.picasoRetail(image: String?, placeHolder: Int) {
    if (image.isNullOrBlank()){
       this?. setImageResource(placeHolder)
    }else
    Picasso.get().load(AppConfig.BASE_URL_IMAGE + image)
            .error(placeHolder)
            .into(this, object : Callback {
        override fun onSuccess() {

        }

        override fun onError(e: java.lang.Exception?) {


        }

    })
}*/







/**
 * Used to scroll to the given view.
 *
 * @param scrollViewParent Parent ScrollView
 * @param view View to which we need to scroll.
 */
fun scrollToView(scrollViewParent: ScrollView, view: View) {
    // Get deepChild Offset
    val childOffset = Point()
    getDeepChildOffset(scrollViewParent, view.parent, view, childOffset)
    // Scroll to child.
    scrollViewParent.smoothScrollTo(0, childOffset.y)
}


fun scrollToViewH(scrollViewParent: HorizontalScrollView, view: View) {
    // Get deepChild Offset
    val childOffset = Point()
    getDeepChildOffset(scrollViewParent, view.parent, view, childOffset)
    // Scroll to child.
    scrollViewParent.smoothScrollTo(0, childOffset.y)
}


/**
 * Used to get deep child offset.
 *
 *
 * 1. We need to scroll to child in scrollview, but the child may not the direct child to scrollview.
 * 2. So to get correct child position to scroll, we need to iterate through all of its parent views till the main parent.
 *
 * @param mainParent        Main Top parent.
 * @param parent            Parent.
 * @param child             Child.
 * @param accumulatedOffset Accumulated Offset.
 */
private fun getDeepChildOffset(
    mainParent: ViewGroup,
    parent: ViewParent,
    child: View,
    accumulatedOffset: Point
) {
    val parentGroup = parent as ViewGroup
    accumulatedOffset.x += child.left
    accumulatedOffset.y += child.top
    if (parentGroup == mainParent) {
        return
    }
    getDeepChildOffset(mainParent, parentGroup.parent, parentGroup, accumulatedOffset)
}


fun RecyclerView?.addItemDecoration(
    @DrawableRes drawableRes: Int,
    orientation: Int = RecyclerView.VERTICAL
) {
    this?.addItemDecoration(DividerItemDecoration(context, orientation).apply {
        ContextCompat.getDrawable(context, drawableRes)?.let { setDrawable(it) }
    })
}

fun TextView.showStrikeThrough(show: Boolean) {
    paintFlags =
        if (show) paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        else paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
}

fun TextView?.wordCapitalize() {
    this?.isAllCaps = false
    this?.setText(text.toString().wordCapitalize())
}

fun String?.wordCapitalize(): String {
    if (this.isNullOrBlank()) {
        return ""
    }
    var s = ""
    toLowerCase().split(" ").forEach {
        if (it.isNotBlank()) {
            s = s + " " + it.replaceFirst(it[0], it[0].toUpperCase())
        }

    }
    return s
}



fun EditText.addSuffix(suffix: String) {
    val editText = this
    val formattedSuffix = " $suffix"
    var text = ""
    var isSuffixModified = false

    val setCursorPosition: () -> Unit =
        { Selection.setSelection(editableText, editableText.length - formattedSuffix.length) }

    val setEditText: () -> Unit = {
        editText.setText(text)
        setCursorPosition()
    }

    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            val newText = editable.toString()

            if (isSuffixModified) {
                // user tried to modify suffix
                isSuffixModified = false
                setEditText()
            } else if (text.isNotEmpty() && newText.length < text.length && !newText.contains(formattedSuffix)) {
                // user tried to delete suffix
                setEditText()
            } else if (!newText.contains(formattedSuffix)) {
                // new input, add suffix
                text = "$newText$formattedSuffix"
                setEditText()
            } else {
                text = newText
            }
        }

        override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
            charSequence?.let {
                val textLengthWithoutSuffix = it.length - formattedSuffix.length
                if (it.isNotEmpty() && start > textLengthWithoutSuffix) {
                    isSuffixModified = true
                }
            }
        }

        override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {

        }
    })
}

fun toRequestBody(value: String?): RequestBody {
    return RequestBody.create("text/plain".toMediaTypeOrNull(), value!!)
}

fun toRequestBody(value: List<String>?): RequestBody {
    return RequestBody.create("text/plain".toMediaTypeOrNull(), value.toString())
}


fun HashMap<String, RequestBody>.addImage(
    file: File?,
    key: String = "profile_image",
    mime: String = "image/jpeg"
): HashMap<String, RequestBody> {
    if (file != null) {
        val requestFile = RequestBody.create(mime.toMediaTypeOrNull(), file)
        this["$key\"; filename=\"" + file.name + "\""] = requestFile
    }
    return this
}


//fun Context.openImageFullScreen(imgUrl: String?){
//    val dialog = Dialog(this)
//    if (dialog.isShowing) dialog.dismiss()
//    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//    dialog.setContentView(R.layout.dialog_image_full_screen_layout)
//    dialog.setCanceledOnTouchOutside(true)
//    dialog.setCancelable(true)
//    dialog.show()
//    dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
//
//    val image = dialog.findViewById<ZoomImageview>(R.id.fullViewImage)
//    val close = dialog.findViewById<ImageView>(R.id.fullViewImageClose)
//
//    Glide.with(this)
//        .asBitmap()
//        .placeholder(R.drawable.group_117)
//        .load(BuildConfig.BASE_IMG_URL+imgUrl)
//        .into(image)
//
//    close.setOnClickListener { dialog.dismiss() }
//}

fun ScrollView.moveToTop(){
    this.isFocusableInTouchMode = true
    this.fullScroll(View.FOCUS_UP)
    this.smoothScrollTo(0,0)
}


//fun Fragment.getDateFromDatePicker(showFuture: Boolean = false,date: (String,String) -> Unit) {
//    var finalDate = ""
//    var toApiDate = ""
//    val dobCal = Calendar.getInstance()
//    val datePicker = DatePickerDialog(
//        requireContext(), R.style.my_dialog_theme,
//        { view, year, month, dayOfMonth ->
//            dobCal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
//            dobCal.set(Calendar.MONTH, month)
//            dobCal.set(Calendar.YEAR, year)
//            finalDate = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).format(dobCal.time)
//            toApiDate = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(dobCal.time)
//            print("DATA: $finalDate $toApiDate")
//            date.invoke(finalDate,toApiDate)
//        }, dobCal.get(Calendar.YEAR), dobCal.get(Calendar.MONTH), dobCal.get(Calendar.DATE)
//    )
//    if (!showFuture)datePicker.datePicker.maxDate = System.currentTimeMillis()
//    datePicker.create()
//    if (!datePicker.isShowing)datePicker.show()
//}
//
//fun Fragment.getTimeFromTimePicker(sate: Int,click:(String) -> Unit){
//    val mcurrentTime = Calendar.getInstance()
//    val hour = mcurrentTime[Calendar.HOUR_OF_DAY]
//    val minute = mcurrentTime[Calendar.MINUTE]
//    val mTimePicker = TimePickerDialog(
//        requireContext(), { timePicker, selectedHour, selectedMinute ->
//
////            val time = Time(hour, minute, 0)
////            val simpleDateFormat = SimpleDateFormat("h:mma")
////            val s = simpleDateFormat.format(time)
//
//            val AM_PM: String = if (selectedHour in 0..11) {
//                "AM"
//            } else {
//                "PM"
//            }
//
//            click.invoke("$selectedHour:$selectedMinute $AM_PM")
////            click.invoke("$selectedHour:$selectedMinute $AM_PM")
////            eReminderTime.setText("$selectedHour:$selectedMinute")
//        },
//        hour,
//        minute,
//        true
//    ) //Yes 24 hour time
//
//    mTimePicker.setTitle(when(sate){
//        1 -> "Time From"
//        else -> "Time To"
//    })
//    mTimePicker.show()
//}

//fun Fragment.havePermission(): Boolean {
//    var permisssn = false
//
//    val permissions = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION)
//    val rationale = "Please provide location permission so that you can ..."
//    val options = Permissions.Options()
//        .setRationaleDialogTitle("Info")
//        .setSettingsDialogTitle("Warning")
//
//    Permissions.check(
//        requireContext() /*context*/,
//        permissions,
//        rationale,
//        options,
//        object : PermissionHandler() {
//            override fun onGranted() {
//                permisssn = true
//            }
//
//            override fun onDenied(context: Context?, deniedPermissions: ArrayList<String?>?) {
//                permisssn = false
//            }
//        })
//    return permisssn
//}

//fun AppCompatActivity.havePermission(): Boolean {
//    var permisssn = false
//
//    val permissions = arrayOf(
//        Manifest.permission.ACCESS_COARSE_LOCATION,
//        Manifest.permission.ACCESS_FINE_LOCATION,
//        Manifest.permission.READ_EXTERNAL_STORAGE,
//        Manifest.permission.CAMERA
//    )
//    val rationale = "Please provide location permission so that you can ..."
//    val options = Permissions.Options()
//        .setRationaleDialogTitle("Info")
//        .setSettingsDialogTitle("Warning")
//
//    Permissions.check(
//        this /*context*/,
//        permissions,
//        rationale,
//        options,
//        object : PermissionHandler() {
//            override fun onGranted() {
//                permisssn = true
//            }
//
//            override fun onDenied(context: Context?, deniedPermissions: ArrayList<String?>?) {
//                permisssn = false
//            }
//        })
//
//    return permisssn
//}


fun InputStream.toFile(path: String) {
    File(path).outputStream().use { this.copyTo(it) }
}

fun setLongTxtData(string: String): String{
    var findString = ""
    findString = if (string.length > 150) {
        Html.fromHtml(string.substring(0, 150) + "..." + "<font color='#CDC4EB'> <u>Continue reading</u></font>").toString()
    } else {
        string
    }

    return findString
}

fun Uri.getFileExtension(context: Context): String? {
    return MimeTypeMap.getSingleton()
        .getExtensionFromMimeType(context.contentResolver.getType(this))
}

//@SuppressLint("MissingPermission")
//fun getLastKnownLocation(context: Context) {
//    val locationManager: LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//    val providers: List<String> = locationManager.getProviders(true)
//    var location: Location? = null
//    for (i in providers.size - 1 downTo 0) {
//        location= locationManager.getLastKnownLocation(providers[i])
//        if (location != null)
//            break
//    }
//    val gps = DoubleArray(2)
//    if (location != null) {
//        gps[0] = location.latitude
//        gps[1] = location.longitude
//        Pref.latitude = gps[0].toString()
//        Pref.longitude = gps[1].toString()
//        Log.e("gpsLat",gps[0].toString())
//        Log.e("gpsLong",gps[1].toString())
//
//    }
//
//}

fun EditText.searchQueryTyped(report: (String) -> Unit) {

    var runnable: Runnable? = null
    var handler: Handler? = null
    val timeToWait = 700L //change this one for delay (time in milli)

    handler = Handler()

    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            runnable = Runnable {
                if (this@searchQueryTyped.text.toString().isEmpty().not()) {
                    report.invoke(this@searchQueryTyped.text.toString())
                }
            }
            handler.postDelayed(runnable!!, timeToWait)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (runnable != null) handler.removeCallbacks(runnable!!)
        }
    })
}

//fun Context.openSingleButtonAlert(title: String?,msg: String?){
//    val dialog = Dialog(this)
//    if (dialog.isShowing) dialog.dismiss()
//    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//    dialog.window!!.setLayout(
//        ActionBar.LayoutParams.MATCH_PARENT,
//        ActionBar.LayoutParams.MATCH_PARENT
//    )
//    val binding: SingleButtonAlertBinding =
//        DataBindingUtil.inflate(dialog.layoutInflater, R.layout.single_button_alert, null, false)
//    dialog.setContentView(binding.root)
//    dialog.setCancelable(true)
//    dialog.show()
//
//    binding.alertMsg.text = msg
//    binding.textView10.text = title
//    binding.alertOk.setOnClickListener { _ ->
//        dialog.dismiss()
//    }
//}

//fun Fragment.openSingleButtonAlert(title: String?,msg: String?){
//    val dialog = Dialog(requireContext())
//    if (dialog.isShowing) dialog.dismiss()
//    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//    dialog.window!!.setLayout(
//        ActionBar.LayoutParams.MATCH_PARENT,
//        ActionBar.LayoutParams.MATCH_PARENT
//    )
//    val binding: SingleButtonAlertBinding =
//        DataBindingUtil.inflate(dialog.layoutInflater, R.layout.single_button_alert, null, false)
//    dialog.setContentView(binding.root)
//    dialog.setCancelable(true)
//    dialog.show()
//
//    binding.alertMsg.text = msg
//    binding.textView10.text = title
//    binding.alertOk.setOnClickListener { _ ->
//        dialog.dismiss()
//    }
//}
//
//fun Fragment.chooseImageFrom(){
//    val dialog = Dialog(requireContext())
//    if (dialog.isShowing) dialog.dismiss()
//    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//    val binding: ImagePickerLytBinding = DataBindingUtil.inflate(dialog.layoutInflater,R.layout.image_picker_lyt,null,false)
//    dialog.setContentView(binding.root)
//    dialog.setCancelable(true)
//    dialog.show()
//
//    binding.CameraChooserBtn.setOnClickListener { _ ->
//        dialog.dismiss()
//        ImagePicker.with(this)
//            .crop()
//            .cameraOnly()
//            .compress(1024)
//            .maxResultSize(1000, 1000)
//            .saveDir(File(requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!, "Saydality"))
//            .start(CAMERA_IMAGE_REQ_CODE)
//    }
//
//    binding.GalleryChooserBtn.setOnClickListener { _ ->
//        dialog.dismiss()
//        ImagePicker.with(this)
//            .crop()
//            .galleryOnly()
//            .galleryMimeTypes(
//                mimeTypes = arrayOf(
//                    "image/png",
//                    "image/jpg",
//                    "image/jpeg"
//                )
//            )
//            .maxResultSize(1000, 1000)
//            .start(GALLERY_IMAGE_REQ_CODE)
//    }
//}

@BindingAdapter("loadGlide")
fun ImageView.loadGlide(url: String?) {
    Glide.with(this.context)
        .load(url)
        .error(R.mipmap.app_icon)
        .into(this)
}


@BindingAdapter("loadDrawable")
fun ImageView.loadDrawable(drawable: Drawable) {
    Glide.with(this.context)
        .load(drawable)
        .error(R.mipmap.app_icon)
        .into(this)
}

fun ImageView.isLiked(isLiked: Boolean) {
    if (isLiked) {
        this.setImageDrawable(ContextCompat.getDrawable(this.context,R.drawable.liked_ico))
    } else {
        this.setImageDrawable(ContextCompat.getDrawable(this.context,R.drawable.unliked_ico))
    }
}

fun ImageView.isCommented(isCommented: Int) {
    if (isCommented == 1) {
        this.setImageDrawable(ContextCompat.getDrawable(this.context,R.drawable.commented_ico))
    } else {
        this.setImageDrawable(ContextCompat.getDrawable(this.context,R.drawable.uncomment_ico))
    }
}

fun getErrorResponse(errorString: JSONObject): String {
    return errorString.optString("data")
}











































