package com.luoxiang.weibo.domain;

/**
 * projectName: 	    WeiBo
 * packageName:	        com.luoxiang.weibo.domain
 * className:	        PublicTimeline
 * author:	            Luoxiang
 * time:	            2016/9/8	10:36
 * desc:	            返回最新的公共微博
 */
public class PublicTimeline {

    /**
     * created_at : Thu Sep 08 10:33:51 +0800 2016
     * id : 4017382253088428
     * mid : 4017382253088428
     * idstr : 4017382253088428
     * text : She says she loves you ，but I need you.
     * textLength : 40
     * source_allowclick : 0
     * source_type : 1
     * source : <a href="http://app.weibo.com/t/feed/3o33sO" rel="nofollow">iPhone 6</a>
     * favorited : false
     * truncated : false
     * in_reply_to_status_id :
     * in_reply_to_user_id :
     * in_reply_to_screen_name :
     */

    private String created_at;
    private long    id;
    private String  mid;
    private String  idstr;
    private String  text;
    private int     textLength;
    private int     source_allowclick;
    private int     source_type;
    private String  source;
    private boolean favorited;
    private boolean truncated;
    private String  in_reply_to_status_id;
    private String  in_reply_to_user_id;
    private String  in_reply_to_screen_name;

    public String getCreated_at() { return created_at;}

    public void setCreated_at(String created_at) { this.created_at = created_at;}

    public long getId() { return id;}

    public void setId(long id) { this.id = id;}

    public String getMid() { return mid;}

    public void setMid(String mid) { this.mid = mid;}

    public String getIdstr() { return idstr;}

    public void setIdstr(String idstr) { this.idstr = idstr;}

    public String getText() { return text;}

    public void setText(String text) { this.text = text;}

    public int getTextLength() { return textLength;}

    public void setTextLength(int textLength) { this.textLength = textLength;}

    public int getSource_allowclick() { return source_allowclick;}

    public void setSource_allowclick(int source_allowclick) { this.source_allowclick = source_allowclick;}

    public int getSource_type() { return source_type;}

    public void setSource_type(int source_type) { this.source_type = source_type;}

    public String getSource() { return source;}

    public void setSource(String source) { this.source = source;}

    public boolean isFavorited() { return favorited;}

    public void setFavorited(boolean favorited) { this.favorited = favorited;}

    public boolean isTruncated() { return truncated;}

    public void setTruncated(boolean truncated) { this.truncated = truncated;}

    public String getIn_reply_to_status_id() { return in_reply_to_status_id;}

    public void setIn_reply_to_status_id(String in_reply_to_status_id) { this.in_reply_to_status_id = in_reply_to_status_id;}

    public String getIn_reply_to_user_id() { return in_reply_to_user_id;}

    public void setIn_reply_to_user_id(String in_reply_to_user_id) { this.in_reply_to_user_id = in_reply_to_user_id;}

    public String getIn_reply_to_screen_name() { return in_reply_to_screen_name;}

    public void setIn_reply_to_screen_name(String in_reply_to_screen_name) { this.in_reply_to_screen_name = in_reply_to_screen_name;}
}
