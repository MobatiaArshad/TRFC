package com.a71cities.trfc.extras

import android.content.Context
import android.content.SharedPreferences
import com.a71cities.trfc.views.signIn.model.LoginData
import com.google.gson.Gson

/**
 * Created by Arshad
 */
object Pref {
    private const val NAME = "BuildConfig.APPLICATION_ID" + "2"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    private val TOKEN = "TOKEN"
    private val USER_ID = "USER_ID"
    private val COUNTRY_CODE = "COUNTRY_CODE"
    private val COUNTRY_NAME = "COUNTRY_NAME"
    private val CURRENCY_CODE = "CURRENCY_CODE"
    private val LOGGED = "ALREADY"
    private val LOCATION = "LOCATION"
    private val GUEST = "GUEST"
    private val DEFAULT_ADDRESS = "DEFAULT_ADDRESS"
    private val USER_NAME = "USER_NAME"
    private val FIREBASE_TOKEN = "FIREBASE_TOKEN"
    private val CART_COUNT = "CART_COUNT"

    private val FORCE_REMINDER = "FORCE_REMINDER"
    private val LANGUAGE = "LANGUAGE"
    private val PROFILE = "PROFILE"
    private val TEMP_PROFILE = "TEMP_PROFILE"
    private val LAT = "LAT"
    private val LNG = "LNG"
    private val USER_PLACE = "USER_PLACE"
    private val USER_LOCALITY = "USER_LOCALITY"
    private val USER_COUNTRY = "USER_COUNTRY"
    private val USER_STATE = "USER_STATE"
    private val USER_PIN = "USER_PIN"
    private val PROFILE_IMG = "PROFILE_IMG"

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var userToken: String
        get() = preferences.getString(TOKEN, "0")!!
        set(value) = preferences.edit {
            it.putString(TOKEN, value)
        }

    var isLogged: Boolean
        get() = preferences.getBoolean(LOGGED, false)
        set(value) = preferences.edit {
            it.putBoolean(LOGGED, value)
        }

    var isLocationSet: Boolean
        get() = preferences.getBoolean(LOCATION, false)
        set(value) = preferences.edit {
            it.putBoolean(LOCATION, value)
        }

    var userId: String
        get() = preferences.getString(USER_ID, "0")!!
        set(value) = preferences.edit {
            it.putString(USER_ID, value)
        }

    var countryId: String
        get() = preferences.getString(COUNTRY_CODE, null)!!
        set(value) = preferences.edit {
            it.putString(COUNTRY_CODE, value)
        }

    var countryName: String
        get() = preferences.getString(COUNTRY_NAME, "")!!
        set(value) = preferences.edit {
            it.putString(COUNTRY_NAME, value)
        }

    var currencyCode: String
        get() = preferences.getString(CURRENCY_CODE, "")!!
        set(value) = preferences.edit {
            it.putString(CURRENCY_CODE, value)
        }

    var deliveryAddressId: String
        get() = preferences.getString(DEFAULT_ADDRESS, null)!!
        set(value) = preferences.edit {
            it.putString(DEFAULT_ADDRESS, value)
        }

    var userName: String
        get() = preferences.getString(USER_NAME, null)!!
        set(value) = preferences.edit {
            it.putString(USER_NAME, value)
        }

    var firebaseToken: String
        get() = preferences.getString(FIREBASE_TOKEN, "0")!!
        set(value) = preferences.edit {
            it.putString(FIREBASE_TOKEN, value)
        }

    var cartCount: Int
        get() = preferences.getInt(CART_COUNT, 0)
        set(value) = preferences.edit {
            it.putInt(CART_COUNT, value)
        }

    var isGuest: Boolean
        get() = preferences.getBoolean(GUEST, false)
        set(value) = preferences.edit {
            it.putBoolean(GUEST, value)
        }

    var isArabic: Boolean
        get() = preferences.getBoolean(LANGUAGE, false)
        set(value) = preferences.edit {
            it.putBoolean(LANGUAGE, value)
        }

    val language_id: String
        get() = if (isArabic) "2" else "1"


//    var temp_profile: TokenGenResponse.TokenData?
//        get() {
//            val s = preferences.getString(TEMP_PROFILE, null) ?: return null
//            val ci = Gson().fromJson<TokenGenResponse.TokenData>(s, TokenGenResponse.TokenData::class.java)
//            return ci
//        }
//        set(value) = preferences.edit {
//            if (value == null)
//                it.putString(TEMP_PROFILE, null)
//            else
//                it.putString(TEMP_PROFILE, Gson().toJson(value))
//        }


    var profile: LoginData?
        get() {
            val s = preferences.getString(PROFILE, null) ?: return null
            val ci = Gson().fromJson<LoginData>(s, LoginData::class.java)
            return ci
        }
        set(value) = preferences.edit {
            if (value == null)
                it.putString(PROFILE, null)
            else
                it.putString(PROFILE, Gson().toJson(value))
        }

    var showReminder: Boolean
        get() = preferences.getBoolean(FORCE_REMINDER, true)
        set(value) = preferences.edit {
            it.putBoolean(FORCE_REMINDER, value)
        }

    var latitude: String
        get() = preferences.getString(LAT, "29.378586")!!
        set(value) = preferences.edit {
            it.putString(LAT, value)
        }

    var longitude: String
        get() = preferences.getString(LNG, "47.990341")!!
        set(value) = preferences.edit {
            it.putString(LNG, value)
        }

    var userPlace: String
        get() = preferences.getString(USER_PLACE, "")!!
        set(value) = preferences.edit {
            it.putString(USER_PLACE, value)
        }

    var userLocality: String
        get() = preferences.getString(USER_LOCALITY, "")!!
        set(value) = preferences.edit {
            it.putString(USER_LOCALITY, value)
        }

    var userCountry: String
        get() = preferences.getString(USER_COUNTRY, "")!!
        set(value) = preferences.edit {
            it.putString(USER_COUNTRY, value)
        }

    var userState: String
        get() = preferences.getString(USER_STATE, "")!!
        set(value) = preferences.edit {
            it.putString(USER_STATE, value)
        }

    var userPIN: String
        get() = preferences.getString(USER_PIN, "")!!
        set(value) = preferences.edit {
            it.putString(USER_PIN, value)
        }

    var profileImg: String?
        get() = preferences.getString(PROFILE_IMG, "")!!
        set(value) = preferences.edit {
            it.putString(PROFILE_IMG, value)
        }
}