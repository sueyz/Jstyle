package com.example.jstyle.util

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import androidx.security.crypto.MasterKeys


object SharedPreferencesUtil {
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {

        // for sharedpreff encryption
        val masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()


        preferences = EncryptedSharedPreferences.create(
            context,
            SharedPreferenceKeys.SHARED_PREFS_FILE.value,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    }

    var isFirstTime: Boolean
        get() {
            return preferences.getBoolean(SharedPreferenceKeys.IS_FIRST_TIME.value, false)
        }
        set(state) {
            preferences.edit().putBoolean(SharedPreferenceKeys.IS_FIRST_TIME.value, state).apply()
        }

    var hasLoggedIn: Boolean
        get() {
            return preferences.getBoolean(SharedPreferenceKeys.HAS_LOGGED_IN.value, false)
        }
        set(state) {
            preferences.edit().putBoolean(SharedPreferenceKeys.HAS_LOGGED_IN.value, state).apply()
        }

    var hasEnabledBiometrics: Boolean
        get() {
            return preferences.getBoolean(SharedPreferenceKeys.HAS_ENABLED_BIOMETRICS.value, false)
        }
        set(state) {
            preferences.edit().putBoolean(SharedPreferenceKeys.HAS_ENABLED_BIOMETRICS.value, state)
                .apply()
        }

    var languagePreference: String?
        get() {
            return preferences.getString(SharedPreferenceKeys.LANGUAGE_PREFERENCE.value, "ENG")
        }
        set(language) {
            preferences.edit().putString(SharedPreferenceKeys.LANGUAGE_PREFERENCE.value, language)
                .apply()
        }

    var storedMobileNumber: String?
        get() {
            return preferences.getString(SharedPreferenceKeys.STORED_MOBILE_NUM.value, "")
        }
        set(language) {
            preferences.edit().putString(SharedPreferenceKeys.STORED_MOBILE_NUM.value, language)
                .apply()
        }

    var appToken: String?
        get() {
            return preferences.getString(SharedPreferenceKeys.APP_TOKEN.value, "")
        }
        set(language) {
            preferences.edit().putString(SharedPreferenceKeys.APP_TOKEN.value, language).apply()
        }

    var registered: Boolean
        get() {
            return preferences.getBoolean(SharedPreferenceKeys.REGISTERED.value, false)
        }
        set(state) {
            preferences.edit().putBoolean(SharedPreferenceKeys.REGISTERED.value, state).apply()
        }

    var viewIntro: Boolean
        get() {
            return preferences.getBoolean(SharedPreferenceKeys.VIEW_INTRO.value, false)
        }
        set(state) {
            preferences.edit().putBoolean(SharedPreferenceKeys.VIEW_INTRO.value, state).apply()
        }

    var storedEmailAddress: String?
        get() {
            return preferences.getString(SharedPreferenceKeys.STORED_EMAIL_ADDRESS.value, "")
        }
        set(language) {
            preferences.edit().putString(SharedPreferenceKeys.STORED_EMAIL_ADDRESS.value, language)
                .apply()
        }

    var userBlCard: String?
        get() {
            return preferences.getString(SharedPreferenceKeys.STORED_CARD_NUM.value, "")
        }
        set(language) {
            preferences.edit().putString(SharedPreferenceKeys.STORED_CARD_NUM.value, language)
                .apply()
        }

    var helpCenterPhone: String?
        get() {
            return preferences.getString(SharedPreferenceKeys.HELP_CENTER_PHONE.value, "0376261000")
        }
        set(value) {
            preferences.edit().putString(SharedPreferenceKeys.HELP_CENTER_PHONE.value, value)
                .apply()
        }

    var helpCenterEmail: String?
        get() {
            return preferences.getString(
                SharedPreferenceKeys.HELP_CENTER_EMAIL.value,
                "memberservices@bonuslink.com.my"
            )
        }
        set(value) {
            preferences.edit().putString(SharedPreferenceKeys.HELP_CENTER_EMAIL.value, value)
                .apply()
        }

    var saveCardWhileLoggedOut: Boolean
        get() {
            return preferences.getBoolean(SharedPreferenceKeys.IS_SAVE_CARD.value, true)
        }
        set(state) {
            preferences.edit().putBoolean(SharedPreferenceKeys.IS_SAVE_CARD.value, state).apply()
        }

    var userEmail: String?
        get() {
            return preferences.getString(SharedPreferenceKeys.USER_EMAIL.value, "email@a.com")
        }
        set(value) {
            preferences.edit().putString(SharedPreferenceKeys.USER_EMAIL.value, value).apply()
        }

    var voucherCode: String?
        get() {
            return preferences.getString(SharedPreferenceKeys.VOUCHER_CODE.value, "")
        }
        set(value) {
            preferences.edit().putString(SharedPreferenceKeys.VOUCHER_CODE.value, value).apply()
        }


    var blPointsRatio: String?
        get() {
            return preferences.getString(SharedPreferenceKeys.BL_POINTS_RATIO.value, "1.00")
        }
        set(value) {
            preferences.edit().putString(SharedPreferenceKeys.BL_POINTS_RATIO.value, value).apply()
        }

    var deviceId: String?
        get() {
            return preferences.getString(SharedPreferenceKeys.DEVICE_INFO.value, "")
        }
        set(value) {
            preferences.edit().putString(SharedPreferenceKeys.DEVICE_INFO.value, value).apply()
        }

    var profileImageFilePath: String?
        get() {
            return preferences.getString(SharedPreferenceKeys.PROFILE_IMAGE_FILE_PATH.value, "")
        }
        set(value) {
            preferences.edit().putString(SharedPreferenceKeys.PROFILE_IMAGE_FILE_PATH.value, value)
                .apply()
        }

    var overThreshold: String?
        get() {
            return preferences.getString(SharedPreferenceKeys.OVER_THRESHOLD.value, "")
        }
        set(value) {
            preferences.edit().putString(SharedPreferenceKeys.OVER_THRESHOLD.value, value).apply()
        }

    var qrCode: String?
        get() {
            return preferences.getString(SharedPreferenceKeys.QRCODE.value, "")
        }
        set(language) {
            preferences.edit().putString(SharedPreferenceKeys.QRCODE.value, language).apply()
        }
    var barCode: String?
        get() {
            return preferences.getString(SharedPreferenceKeys.BARCODE.value, "")
        }
        set(language) {
            preferences.edit().putString(SharedPreferenceKeys.BARCODE.value, language).apply()
        }

    var pinTrialCount: Int
        get() {
            return preferences.getInt(SharedPreferenceKeys.PIN_TRIAL_COUNT.value, 2)
        }
        set(value) {
            preferences.edit().putInt(SharedPreferenceKeys.PIN_TRIAL_COUNT.value, value).apply()
        }

    var otpLockTime: Int
        get() {
            return preferences.getInt(SharedPreferenceKeys.OTP_LOCKTIME.value, 5)
        }
        set(value) {
            preferences.edit().putInt(SharedPreferenceKeys.OTP_LOCKTIME.value, value).apply()
        }

    var tncVersion: String?
        get() {
            return preferences.getString(SharedPreferenceKeys.TNC_VERSION.value, "")
        }
        set(language) {
            preferences.edit().putString(SharedPreferenceKeys.TNC_VERSION.value, language).apply()
        }

    var pointTransThreshold: String?
        get() {
            return preferences.getString(BundleKeys.POINT_TRANS_THRESHOLD, "2000")
        }
        set(value) {
            preferences.edit().putString(BundleKeys.POINT_TRANS_THRESHOLD, value).apply()
        }
    var otpResendDuration: String?
        get() {
            return preferences.getString(BundleKeys.OTP_RESEND_DURATION, "5")
        }
        set(value) {
            preferences.edit().putString(BundleKeys.OTP_RESEND_DURATION, value).apply()
        }
    var paymentTransThreshold: String?
        get() {
            return preferences.getString(BundleKeys.PAYMENT_TRANS_THRESHOLD, "200")
        }
        set(value) {
            preferences.edit().putString(BundleKeys.PAYMENT_TRANS_THRESHOLD, value).apply()
        }
    var otpTrialCount: String?
        get() {
            return preferences.getString(BundleKeys.OTP_TRIAL_COUNT, "3")
        }
        set(value) {
            preferences.edit().putString(BundleKeys.OTP_TRIAL_COUNT, value).apply()
        }
    var registrationMaxCardMerge: String?
        get() {
            return preferences.getString(BundleKeys.REG_MAX_CARD_MERGE, "5")
        }
        set(value) {
            preferences.edit().putString(BundleKeys.REG_MAX_CARD_MERGE, value).apply()
        }
    var qrSharingDuration: String?
        get() {
            return preferences.getString(BundleKeys.QR_SHARING_DURATION, "1")
        }
        set(value) {
            preferences.edit().putString(BundleKeys.QR_SHARING_DURATION, value).apply()
        }
    var maxEmailAdd: String?
        get() {
            return preferences.getString(BundleKeys.MAX_EMAIL_ADD, "0")
        }
        set(value) {
            preferences.edit().putString(BundleKeys.MAX_EMAIL_ADD, value).apply()
        }

    var voucherNearbyDistance: String?
        get() {
            return preferences.getString(BundleKeys.VOUCHER_NEARBY_DISTANCE, "5")
        }
        set(value) {
            preferences.edit().putString(BundleKeys.VOUCHER_NEARBY_DISTANCE, value).apply()
        }
    var voucherPageSize: String?
        get() {
            return preferences.getString(BundleKeys.VOUCHER_PAGE_SIZE, "20")
        }
        set(value) {
            preferences.edit().putString(BundleKeys.VOUCHER_PAGE_SIZE, value).apply()
        }
    var merchantOtpResendDuration: String?
        get() {
            return preferences.getString(BundleKeys.MERCHANT_OTP_RESEND_DURATION, "10")
        }
        set(value) {
            preferences.edit().putString(BundleKeys.MERCHANT_OTP_RESEND_DURATION, value).apply()
        }
    var merchantOtpAttemptCount: String?
        get() {
            return preferences.getString(BundleKeys.MERCHANT_OTP_ATTEMPT_COUNT, "3")
        }
        set(value) {
            preferences.edit().putString(BundleKeys.MERCHANT_OTP_ATTEMPT_COUNT, value).apply()
        }
    var merchantMaxDistance: String?
        get() {
            return preferences.getString(BundleKeys.MERCHANT_MAX_DISTANCE, "5")
        }
        set(value) {
            preferences.edit().putString(BundleKeys.MERCHANT_MAX_DISTANCE, value).apply()
        }
    var giftingDuration: String?
        get() {
            return preferences.getString(BundleKeys.GIFTING_DURATION, "86400")
        }
        set(value) {
            preferences.edit().putString(BundleKeys.GIFTING_DURATION, value).apply()
        }
    var promotionPageSize: String?
        get() {
            return preferences.getString(BundleKeys.PROMOTION_PAGE_SIZE, "10")
        }
        set(value) {
            preferences.edit().putString(BundleKeys.PROMOTION_PAGE_SIZE, value).apply()
        }
    var merchantPageSize: String?
        get() {
            return preferences.getString(BundleKeys.MERCHANT_PAGE_SIZE, "5")
        }
        set(value) {
            preferences.edit().putString(BundleKeys.MERCHANT_PAGE_SIZE, value).apply()
        }
    var referralPrefix: String?
        get() {
            return preferences.getString(BundleKeys.REFERRAL_PREFIX, "B2")
        }
        set(value) {
            preferences.edit().putString(BundleKeys.REFERRAL_PREFIX, value).apply()
        }
    var referralLength: String?
        get() {
            return preferences.getString(BundleKeys.REFERRAL_LENGTH, "8")
        }
        set(value) {
            preferences.edit().putString(BundleKeys.REFERRAL_LENGTH, value).apply()
        }
    var ipay88non3dLimit: String?
        get() {
            return preferences.getString(BundleKeys.IPAY88NON3D_LIMIT, "250")
        }
        set(value) {
            preferences.edit().putString(BundleKeys.IPAY88NON3D_LIMIT, value).apply()
        }
    var merchantSendReportDuration: String?
        get() {
            return preferences.getString(BundleKeys.MERCHANT_SEND_REPORT_DURATION, "6")
        }
        set(value) {
            preferences.edit().putString(BundleKeys.MERCHANT_SEND_REPORT_DURATION, value).apply()
        }
    var merchantIssuePointMax: String?
        get() {
            return preferences.getString(BundleKeys.MERCHANT_ISSUE_POINT_MAX, "1000")
        }
        set(value) {
            preferences.edit().putString(BundleKeys.MERCHANT_ISSUE_POINT_MAX, value).apply()
        }
    var merchantTutorialUrl: String?
        get() {
            return preferences.getString(
                BundleKeys.MERCHANT_TUTORIAL_URL,
                "https://www.youtube.com/watch?v=svDLq6cDouI"
            )
        }
        set(value) {
            preferences.edit().putString(BundleKeys.MERCHANT_TUTORIAL_URL, value).apply()
        }
    var merchantHelpCenterPhone: String?
        get() {
            return preferences.getString(BundleKeys.MERCHANT_HELP_CENTER_PHONE, "03-7626 1000")
        }
        set(value) {
            preferences.edit().putString(BundleKeys.MERCHANT_HELP_CENTER_PHONE, value).apply()
        }
    var merchantNotificationDuration: String?
        get() {
            return preferences.getString(BundleKeys.MERCHANT_NOTIFICATION_DURATION, "60")
        }
        set(value) {
            preferences.edit().putString(BundleKeys.MERCHANT_NOTIFICATION_DURATION, value).apply()
        }
    var rewardSignupDuration: String?
        get() {
            return preferences.getString(BundleKeys.REWARD_SIGN_UP_DURATION, "30")
        }
        set(value) {
            preferences.edit().putString(BundleKeys.REWARD_SIGN_UP_DURATION, value).apply()
        }
    var rewardBirthdayDuration: String?
        get() {
            return preferences.getString(BundleKeys.REWARD_BIRTHDAY_DURATION, "30")
        }
        set(value) {
            preferences.edit().putString(BundleKeys.REWARD_BIRTHDAY_DURATION, value).apply()
        }
    var rewardBirthdayDaysFromBirthday: String?
        get() {
            return preferences.getString(BundleKeys.REWARD_BIRTHDAY_DAYS_FROM_BIRTHDAY, "10")
        }
        set(value) {
            preferences.edit().putString(BundleKeys.REWARD_BIRTHDAY_DAYS_FROM_BIRTHDAY, value)
                .apply()
        }
    var regTrialCount: String?
        get() {
            return preferences.getString(BundleKeys.REG_TRIAL_COUNT, "5")
        }
        set(value) {
            preferences.edit().putString(BundleKeys.REG_TRIAL_COUNT, value).apply()
        }
    var merchantOutletDetailPageSize: String?
        get() {
            return preferences.getString(BundleKeys.MERCHANT_OUTLET_DETAIL_PAGE_SIZE, "10")
        }
        set(value) {
            preferences.edit().putString(BundleKeys.MERCHANT_OUTLET_DETAIL_PAGE_SIZE, value).apply()
        }
    var merchantFindTransactionPageSize: String?
        get() {
            return preferences.getString(BundleKeys.MERCHANT_FIND_TRANSACTION_PAGE_SIZE, "10")
        }
        set(value) {
            preferences.edit().putString(BundleKeys.MERCHANT_FIND_TRANSACTION_PAGE_SIZE, value)
                .apply()
        }
    var merchantNotificationPageSize: String?
        get() {
            return preferences.getString(BundleKeys.MERCHANT_NOTIFICATION_PAGE_SIZE, "10")
        }
        set(value) {
            preferences.edit().putString(BundleKeys.MERCHANT_NOTIFICATION_PAGE_SIZE, value).apply()
        }
    var stateModifiedDate: String?
        get() {
            return preferences.getString(BundleKeys.STATE_MODIFIED_DATE, "2015-02-12 16:16:08.130")
        }
        set(value) {
            preferences.edit().putString(BundleKeys.STATE_MODIFIED_DATE, value).apply()
        }
    var selectedPoolId: String?
        get() {
            return preferences.getString(BundleKeys.SELECTED_POOL_ID, "")
        }
        set(value) {
            preferences.edit().putString(BundleKeys.SELECTED_POOL_ID, value).apply()
        }

    var referralOn: Boolean
        get() {
            return preferences.getBoolean(SharedPreferenceKeys.REFERRAL_ON.value, false)
        }
        set(value) {
            preferences.edit().putBoolean(SharedPreferenceKeys.REFERRAL_ON.value, value).apply()
        }

    var dashboardDefaultPage: Int
        get() {
            return preferences.getInt(BundleKeys.dashboardTab, 0)
        }
        set(value) {
            preferences.edit().putInt(BundleKeys.dashboardTab, value).apply()
        }

    var refreshUserProfile: Boolean
        /* This feature is for refreshing the user points after
            user perform transaction that will increase/decrease
            the point
         */
        get() {
            return preferences.getBoolean(SharedPreferenceKeys.REFRESH_USER_PROFILE.value, false)
        }
        set(value) {
            preferences.edit().putBoolean(SharedPreferenceKeys.REFRESH_USER_PROFILE.value, value)
                .apply()
        }

    private enum class SharedPreferenceKeys(val value: String) {
        SHARED_PREFS_FILE("bonuslinksharedprefs"),
        IS_FIRST_TIME("isFirstTime"),
        HAS_LOGGED_IN("hasLoggedIn"),
        HAS_ENABLED_BIOMETRICS("hasEnabledBiometrics"),
        LANGUAGE_PREFERENCE("languagePreference"),
        STORED_MOBILE_NUM("storedMobileNumber"),
        APP_TOKEN("appToken"),
        FORCE_UPDATE("forceUpdate"),
        REGISTERED("registered"),
        VIEW_INTRO("viewIntro"),
        STORED_EMAIL_ADDRESS("storedEmailAddress"),
        STORED_CARD_NUM("storedCardNumber"),
        HELP_CENTER_PHONE("helpCenterPhone"),
        HELP_CENTER_EMAIL("helpCenterEmail"),
        IS_SAVE_CARD("saveCardWhileLoggedOut"),
        USER_EMAIL("userEmail"),
        VOUCHER_CODE("voucherCode"),
        BL_POINTS_RATIO("blPointsRatio"),
        PROFILE_IMAGE_FILE_PATH("profileImageFilePath"),
        DEVICE_INFO("deviceInfo"),
        OVER_THRESHOLD("overThreshold"),
        QRCODE("qr_code"),
        BARCODE("bar_code"),
        PIN_TRIAL_COUNT("pin.trialcount"),
        TNC_VERSION("tncVersion"),
        OTP_LOCKTIME("otp.locktime"),
        SELECTED_POOL_ID("selected.pool.id"),
        REFERRAL_ON("referralon"),
        REFRESH_USER_PROFILE("refreshUserProfile"),
    }
}