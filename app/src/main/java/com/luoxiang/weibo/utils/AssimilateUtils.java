package com.luoxiang.weibo.utils;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Pair;
import android.view.View;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String 处理工具类
 */
public class AssimilateUtils {

    // @thanatosx
    // http://my.oschina.net/u/user_id
    // http://my.oschina.net/user_ident
    public static final Pattern PatternAtUserWithHtml = Pattern.compile(
            "<a\\s+href=['\"]http[s]?://my\\.oschina\\.[a-z]+/([0-9a-zA-Z_]+" +
                    "|u/([0-9]+))['\"][^<>]*>(@([^@<>\\s]+))</a>"
    );
    public static final Pattern PatternAtUser         = Pattern.compile(
            "@[^@\\s:]+"
    );

    // #Java#
    public static final Pattern PatternSoftwareTagWithHtml = Pattern.compile(
            "<a\\s+href=['\"]([^'\"]*)['\"][^<>]*>(#[^#@<>\\s]+#)</a>"
    );
    public static final Pattern PatternSoftwareTag         = Pattern.compile(
            "#([^#@<>\\s]+)#"
    );

    // @user links
    @Deprecated
    public static final Pattern PatternAtUserAndLinks = Pattern.compile(
            "<a\\s+href=['\"]http://my\\.oschina\\.net/([0-9a-zA-Z_]+)['\"][^<>]*>(@[^@<>\\s]+)</a>" +
                    "|<a href=['\"]([^'\"]*)['\"][^<>]*>([^<>]*)</a>"
    );

    // links
    public static final Pattern PatternLinks = Pattern.compile(
            "<a\\s+href=['\"]([^'\"]*)['\"][^<>]*>([^<>]*)</a>"
    );

    // team task
    public static final Pattern PatternTeamTask = Pattern.compile(
            "<a\\s+style=['\"][^'\"]*['\"]\\s+href=['\"]([^'\"]*)['\"][^<>]*>([^<>]*)</a>"
    );

    // html task
    public static final Pattern PatternHtml = Pattern.compile(
            "<[^<>]+>([^<>]+)</[^<>]+>"
    );

    private interface Action1 {
        void call(String str);
    }


    /**
     * 高亮@User
     *
     * @param context Context
     * @param content string
     * @return
     */
    public static Spannable highlightAtUser(Context context, CharSequence content) {
        return highlightAtUser(context, new SpannableString(content));
    }

    /**
     * @param context   Context
     * @param spannable string
     * @return
     * @see #highlightAtUser(Context, Spannable)
     */
    public static Spannable highlightAtUser(Context context, Spannable spannable) {
        String  str     = spannable.toString();
        Matcher matcher = PatternAtUser.matcher(str);
        while (matcher.find()) {
            ForegroundColorSpan span = new ForegroundColorSpan(0XFF6888AD);
            spannable.setSpan(span, matcher.start(), matcher.end(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        return spannable;
    }

    public static Spannable clearHtmlTag(CharSequence content) {
        SpannableStringBuilder builder = new SpannableStringBuilder(content);
        Matcher                matcher;
        while (true) {
            matcher = PatternHtml.matcher(builder.toString());
            if (matcher.find()) {
                String str = matcher.group(1);
                builder.replace(matcher.start(), matcher.end(), str);
                continue;
            }
            break;
        }
        return builder;
    }

    /**
     * 本地化处理
     *
     * @param content 处理内容
     * @param pattern 匹配规则
     * @param index0  使用的组号
     * @param index1  显示的组号
     * @param action  回调函数
     * @return Spannable
     */
    @Deprecated
    private static Spannable assimilate(String content, Pattern pattern, int index0, int index1,
                                        final Action1 action) {
        Matcher                             matcher;
        Map<String, Pair<Integer, Integer>> maps = new HashMap<>();

        while (true) {
            matcher = pattern.matcher(content);
            if (matcher.find()) {
                String group0 = matcher.group(index0);
                String group1 = matcher.group(index1);
                content = matcher.replaceFirst(group1);
                maps.put(group0, new Pair<>(matcher.start(), matcher.start() + group1.length()));
                continue;
            }
            break;
        }

        Spannable spannable = new SpannableString(content);

        for (final Map.Entry<String, Pair<Integer, Integer>> entry : maps.entrySet()) {
            final String                 substr = entry.getKey();
            final Pair<Integer, Integer> pair   = entry.getValue();
            ClickableSpan span = new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    action.call(substr);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    ds.setColor(ds.linkColor);
                    ds.setUnderlineText(false);
                }
            };
            spannable.setSpan(span, pair.first, pair.second, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        }
        return spannable;
    }

    /**
     * 本地化处理
     *
     * @param sequence 处理内容
     * @param pattern  匹配规则
     * @param index0   使用的组号
     * @param index1   显示的组号
     * @param action   回调函数
     * @return Spannable
     */
    private static Spannable assimilate(CharSequence sequence, Pattern pattern, int index0,
                                        int index1, final Action1 action) {
        SpannableStringBuilder builder = new SpannableStringBuilder(sequence);
        Matcher                matcher;
        while (true) {
            matcher = pattern.matcher(builder.toString());
            if (matcher.find()) {
                final String group0 = matcher.group(index0);
                final String group1 = matcher.group(index1);
                builder.replace(matcher.start(), matcher.end(), group1);
                ClickableSpan span = new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        action.call(group0);
                    }
                };
                builder.setSpan(span, matcher.start(), matcher.start() + group1.length(),
                                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                continue;
            }
            break;
        }
        return builder;
    }


    public static boolean machPhoneNum(CharSequence phoneNumber) {

        String regex = "^[1][34578][0-9]\\d{8}$";
        // Pattern pattern = Pattern.compile(regex);
        // pattern.matcher(phoneNumber).matches();

        //第二种就是对一种的一种封装
        return Pattern.matches(regex, phoneNumber);
    }

    public static boolean machEmail(CharSequence email) {
        String regex = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
        return Pattern.matches(regex, email);
    }


    public static boolean checkIsZH(String input) {
        char[] charArray = input.toLowerCase().toCharArray();
        for (char c : charArray) {
            String tempC = Character.toString(c);
            if (tempC.matches("[\u4E00-\u9FA5]+")) {
                return true;
            }
        }
        return false;
    }


    /**
     * 获取最后一个汉字的坐标
     *
     * @param text 可能含有拼音的字符串
     * @return 如果未查询到返回-1，否则返回最后一个汉字所在的坐标
     */
    public static int lastIndexOfChinese(String text) {
        char[] charArray = text.toCharArray();
        int index = -1;
        for (int i = 0; i < charArray.length; i++) {
            String tempC = Character.toString(charArray[i]);
            if (tempC.matches("[\u4E00-\u9FA5]+")) {
                index = i;
            }
        }
        return index;
    }

}
