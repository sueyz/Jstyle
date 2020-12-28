package com.example.jstyle.util

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.firebase.analytics.FirebaseAnalytics
import timber.log.Timber

object FirebaseAnalyticsUtil {

    var screenName = ScreenName.NONE
    var userBlCard = ""

    enum class ScreenName(val value: String) {
        NONE("none"),
        ADDITIONALINFORMATION("additional_information_screen"),
        APPSETTINGS("app_settings_screen"),
        DASHBOARD("dashboard_screen"),
        LOGIN("login_screen"),
        BIOMETRICENROLL("biometric_enroll_screen"),
        BLCARDLANDING("bl_card_landing_screen"),
        BLCARDADDCARD("bl_card_add_card_screen"),
        BLCARDSUBMISSION("bl_card_submission_screen"),
        CARDAUTHENTICATION("card_authentication_screen"),
        CARDFOUND("card_found_screen"),
        CHANGEMOBILENUMBER("change_mobile_number_screen"),
        CHANGENRICPASSPORT("change_nric_passport_screen"),
        CONTACTFORM("contact_form_screen"),
        CREDITCARDDETAILS("credit_card_details_screen"),
        DEALS("deals_landing_screen"),
        DEALSMERCHANT("deals_merchant_screen"),
        DETAILSSUBMISSION("registration_details_screen"),
        FORGOTPIN("reset_pin_screen"),
        GENERALQUESTIONS("general_questions_screen"),
        GIFTQRLISTING("gift_qr_listing_screen"),
        GIFT("gift_screen"),
        HELPSUPPORT("help_support_screen"),
        HELPSUPPORTSEARCH("help_support_search_screen"),
        INTROFRAG("intro_screen"),
        ISSUETRANSACTIONREPORT("issue_transaction_report_screen"),

        KEYINADDRESS("key_in_address_screen"),
        KEYINADDRESSDEFAULT("key_in_address_default_screen"),
        KEYINAMOUNT("key_in_amount_screen"),
        LOGOUT("logout_screen"),
        MAIN("main_screen"),

        MERCHANTVERIFICATION("merchant_verification_screen"),
        MYWALLET("my_wallet_screen"),
        NOTIFICATIONDETAIL("notification_detail_screen"),
        NOTIFICATIONLIST("notification_list_screen"),
        NUMBERNOTFOUND("number_not_found_screen"),
        OTP("registration_otp_screen"),
        PARTICIPATINGOUTLET("voucher_participating_outlets_screen"),
        PASSWORDANDSECURITY("password_and_security_screen"),
        MANAGEPAYMENTCARD("manage_payment_card_screen"),
        ADDPAYMENTCARD("add_payment_card_screen"),
        PAYMENTCONFIRMATION("payment_confirmation_screen"),
        PAYMENTSUCCESS("payment_success_screen  "),
        CREDITCARDLISTING("credit_card_listing_screen  "),
        POINTEXPIRYFORECAST("point_expiry_forecast_screen  "),
        BLPOINTTRANSACTIONEXPORT("bl_point_transaction_export_screen"),
        POINTTRANSACTION("point_transaction_screen   "),
        ACCOUNT("account_screen"),
        PROFILEDETAILS("profile_details_screen"),
        PROMOTION("promotion_screen "),
        PROMOTIONLISTING("promotion_listing_screen"),
        PROMOTIONDETAILS("promotion_details_screen"),
        PROMPTMESSAGEFRAGMENT("prompt_message_screen"),
        QRGENERATORFRAGMENT("qr_generator_screen  "),
        QRSCANNER("qr_scanner_screen   "),

        REFERRALCODE("referral_code_screen"),
        REQUESTCARD("request_card_screen"),
        RESETPINFAILED("reset_pin_failed_screen"),
        SEARCHFAQS("search_faqs_screen"),
        SELECTEDOUTLET("selected_outlet_screen"),
        SETPIN("set_pin_screen"),
        SETNEWPIN("set_new_pin_screen"),
        SHOWQR("show_qr_screen"),
        SIGNUP1("sign_up_1_screen"),
        SIGNUP2("sign_up_2_screen"),
        TERMANDCONDITIONS("term_and_conditions_screen"),
        TRANSACTIONHISTORYDETAILS("transaction_history_details_screen"),
        TRANSACTIONHISTORYLISTING("transaction_history_listing_screen"),
        VOUCHERLIST("voucher_list_screen"),
        WEBVIEWACTIVITY("web_view_activity_screen"),
        WELCOME("welcome_screen"),
        DEALCHANGELOCATION("deal_change_location_screen"),
        VOUCHERMERCHANTDETAILS("voucher_merchant_details_screen"),
        VOUCHERDETAILS("voucher_details_screen"),
        MERCHANTLISTING("merchant_listing_screen"),
        DEALSLISTING("deals_listing_screen"),
        VOUCHERBEFOREPURCHASE("voucher_beforepurchase_screen"),
        MERCHANTDETAILS("merchant_detail_screen"),
        CASHBACKDETAILS("cashback_details_screen"),
        VOUCHERPAYMENT("voucher_payment_screen"),
        MERCHANTPAYMENT("payment_to_merchant_screen"),
        VOUCHERPAYSUCCESS("voucher_payment_success_screen"),
        DONATIONPAYSUCCESS("donation_payment_success_screen"),
        MERCHANTPAYSUCCESS("payment_to_merchant_success_screen"),
        VOUCHERPURCHASED("voucher_afterpurchase_screen"),
    }

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    fun init(context: Context) {
        this.firebaseAnalytics = FirebaseAnalytics.getInstance(context)
    }

    fun isInitialized() = this::firebaseAnalytics.isInitialized

    fun logCustomEvent(event: String, values: Bundle?) {
        if (isInitialized()) {

            //Set the default param and value for tracking
            values!!.putString(EventParam.SCREENNAME, screenName.value)
            values.putString(EventParam.BLCARDNUM, SharedPreferencesUtil.userBlCard)

            Timber.d("FA Log Custom Event String:" + event)
            Timber.d("FA Log Custom Event bundle:" + values.toString())

            firebaseAnalytics.logEvent(event, values)

            // Reset the correct param to the default
            values.clear()
            values.putString(EventParam.BLCARDNUM, SharedPreferencesUtil.userBlCard)
            values.putString(EventParam.SCREENNAME, screenName.value)
        } else {
            throw NullPointerException("Firebase Analytics is not initialized.")
        }
    }

    fun logScreen(frag: Fragment, screenName: ScreenName, screenClassOverride: String? = null) {
        if (isInitialized()) {
            logScreen(frag.requireActivity(), screenName, screenClassOverride)
        } else {
            throw NullPointerException("Firebase Analytics is not initialized.")
        }
    }

    private fun logScreen(
        activity: Activity,
        screenName: ScreenName,
        screenClassOverride: String? = null
    ) {
        if (isInitialized()) {
            if (screenName != ScreenName.NONE) {
                Timber.d("FA Log Custom screen:%s", screenName.value)
                this.screenName = screenName
                firebaseAnalytics.setCurrentScreen(activity, screenName.value, screenClassOverride)
            }
        } else {
            throw NullPointerException("Firebase Analytics is not initialized.")
        }
    }

    object AnalyticsEvent {
        const val LOGIN_BUTTON = "login_button"
        const val REGISTER_HERE_BUTTON = "register_here_button"
        const val RESET_PIN_BUTTON = "reset_pin_button"
        const val VIRTUAL_CARD_SELECTED = "virtual_card_selected"
        const val SKIP_BUTTON = "skip_button"
        const val RESEND_CODE_SELECTED = "resend_code_selected"
        const val VERIFY_BUTTON = "verify_button"
        const val NEXT_BUTTON = "next_button"
        const val BACK_BUTTON = "back_button"
        const val CONTINUE_BUTTON = "continue_button"
        const val PROCEED_BUTTON = "proceed_button"
        const val VIEW_MERCHANT_LISTING = "view_merchant_listing"
        const val VOUCHER_LISTING_FILTER = "voucher_listing_filter"
        const val VOUCHER_TYPE_BUTTON = "voucher_type_button"
        const val CATEGORY_BUTTON = "category_button"
        const val NEARBY_DEALS_VIEW_ALL = "nearby_deals_view_all"
        const val HOTPICKS_VIEW_ALL = "hotpicks_view_all"
        const val NEWLY_ADDED_VIEW_ALL = "newly_added_view_all"
        const val MY_WALLET_VIEW = "my_wallet_view"
        const val NOTIFICATION_BUTTON = "notification_button"
        const val SCANNER_BUTTON = "scanner_button"
        const val NEARBY_DEALS_BUTTON = "nearby_deals_button"
        const val MY_WALLET_BUTTON = "my_wallet_button"
        const val BL_CARD_BUTTON = "bl_card_button"
        const val PROMO_VIEW_ALL_BUTTON = "promo_view_all_button"
        const val PROMOTION_BUTTON = "promotion_button"
        const val PARTICIPATING_OUTLET_BUTTON = "participating_outlet_button"
        const val TNC_BUTTON = "tnc_button"
        const val GET_DIRECTION = "get_direction_button"
        const val FILTER_OUTLET = "filter_outlet_button"
        const val BUY_NOW_BUTTON = "buy_now_button"
        const val NEARBY_OUTLET_BUTTON = "nearby_outlet_button"
        const val MERCHANT_DETAIL_BUTTON = "merchant_detail_button"
        const val FILTER_VOUCHER_BUTTON = "filter_voucher_button"
        const val CHANGE_WALLET = "change_wallet"
        const val PAYMENT_METHOD_BUTTON = "payment_method_button"
        const val PAY_BUTTON = "pay_button"
        const val CLOSE_BUTTON = "close_button"
        const val VIEW_WALLET_BUTTON = "view_wallet_button"
        const val DONE_BUTTON = "done_button"
        const val SWIPE2REDEEM = "swipe_to_redeem"
        const val SHOWCODE = "show_code"
        const val SHAREVOUCHERBUTTON = "share_voucher_button"
        const val VIEW_POINT_EXPIRY_FORECAST = "view_point_expiry_forecast"
        const val VIEW_BLPOINT_TRANSACTION_BUTTON = "view_blpoint_transaction_button"
        const val SEARCH_BUTTON = "search_button"
        const val HELPCENTERFILTER = "help_center_filter"
        const val HELPCENTERCALL = "help_center_call"
        const val HELPCENTERCONTACTFORM = "help_center_contact_form"
        const val NOTIFICATIONREAD = "notification_read"
        const val MARKALLREAD = "mark_all_read"
        const val EXPORTPOINTTRANSACTION = "export_point_transaction"

        // region Account / Profile
        const val PROFILE_PICTURE_BUTTON: String = "profile_picture_button"
        const val EDIT_PROFILE_BUTTON: String = "edit_profile_button"
        const val VIEW_BLCARD_BUTTON: String = "view_blcard_button"
        const val TRANSACTION_HISTORY_BUTTON: String = "transaction_history_button"
        const val MANAGE_PAYMENT_BUTTON: String = "manage_payment_button"
        const val SETTINGS_BUTTON: String = "settings_button"
        const val REFER_A_FRIEND_BUTTON: String = "refer_a_friend_button"
        const val HELP_SUPPORT_BUTTON: String = "help_support_button"
        const val LOGOUT_BUTTON: String = "logout_button"

        // endregion

        const val FILTER_TRANSACTION: String = "filter_transaction_button"
        const val EXPORT_TRANSACTION: String = "export_transaction_button"
        const val PAYMENT_CARD_REMOVE_CARD: String = "remove_card_button"
        const val PAYMENT_CARD_ADD_CARD: String = "add_card_button"
        const val PAYMENT_CARD_SET_PRIMARY_CARD: String = "set_primary_button"
        const val PAYMENT_CARD_ADD_BUTTON: String = "add_button"
        const val PAYMENT_CARD_BACK_BUTTON: String = "back_button"

    }

    object EventParam {
        const val BLCARDNUM = "bl_card_num"
        const val SCREENNAME = "screen_name"
        const val CATEGORYNAME = "category_name"
        const val NOTIFICATIONID = "notification_id"
        const val TRANSACTIONTYPE = "transaction_type"
        const val FILTERSTARTDATE = "filter_start_date"
        const val FILTERENDDATE = "filter_end_date"
        const val REFERRALCODE = "referral_code"
        const val LATITUDE = "latitude"
        const val LONGITUDE = "longitude"
        const val SEARCHVALUE = "search_value"
        const val CATEGORY = "category"
        const val PRICE = "price"
        const val TYPE = "type"
        const val CTATYPE = "ctatype"
        const val PROMOTIONID = "promotion_id"
        const val MERCHANTID = "merchant_id"
        const val OUTLET_NAME = "outlet_name"
        const val OUTLET_ID = "outlet_id"
        const val FILTERTYPE = "filter_type"
        const val WALLETTYPE = "wallet_type"
        const val VOUCHERID = "voucher_id"
        const val PAYMENTAMOUNT = "payment_amount"
        const val PAYMENTTYPE = "payment_type"
        const val PAYMENTID = "payment_id"
        const val TRANSACTION_HISTORY_FILTER_TRANSACTION_TYPE: String = "transaction_type"
        const val TRANSACTION_HISTORY_FILTER_TRANSACTION_START_DATE: String = "filter_start_date"
        const val TRANSACTION_HISTORY_FILTER_TRANSACTION_END_DATE: String = "filter_end_date"
        const val TRANSACTION_DETAIL_EXPORT_TRANSACTION_ID: String = "transaction_id"
        const val PAYMENT_CARD_ID: String = "card_id"
    }

}