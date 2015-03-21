package com.taurusxi.androidcommon.utils;


/**
 * Created on 15/2/27.
 *
 * @author xicheng
 * @email 505591443@qq.com
 * @github https://github.com/TaurusXi
 */
public class StringUtils {

    public static String formatNull(String source) {
        return source == null ? "" : source;
    }

    public static String formatNull(String source, String defaultStr) {
        return source == null ? defaultStr : source;
    }


    public static String httpsToHttp(String source) {
        return source.replace("https", "http");
    }

//    public static SpannableStringBuilder getClickedURL(String s, final Activity context) {
//        Spannable spannable = (Spannable) Html.fromHtml(s);
//        URLSpan[] urls = spannable.getSpans(0, spannable.length(), URLSpan.class);
//        SpannableStringBuilder style = new SpannableStringBuilder(spannable);
//        style.clearSpans();// should clear old spans
//        for (final URLSpan url : urls) {
//            int start = spannable.getSpanStart(url);
//            int end = spannable.getSpanEnd(url);
//            style.setSpan(new MyURLSpan(url.getURL(), spannable.subSequence(start, end).toString(), context), start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
//        }
//        return style;
//    }

    public static String formatLength(String title, int length) {
        if (title.length() > 15) {
            return title.subSequence(0, 15) + "...";
        }
        return title;
    }
}
