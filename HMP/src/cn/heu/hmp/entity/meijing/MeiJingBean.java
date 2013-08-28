package cn.heu.hmp.entity.meijing;

public class MeiJingBean {
	
	private String tp_id="";
	private String tp_name="";
	private String tp_rq="";
	private String tp_desc="";
	private String tp_bigImgurl="";
	private String tp_smallImgurl="";
	
	public String getTp_id() {
		return tp_id;
	}
	public void setTp_id(String tp_id) {
		this.tp_id = tp_id;
	}
	public String getTp_name() {
		return tp_name;
	}
	public void setTp_name(String tp_name) {
		this.tp_name = tp_name;
	}
	public String getTp_rq() {
		return tp_rq;
	}
	public void setTp_rq(String tp_rq) {
		this.tp_rq = tp_rq;
	}
	public String getTp_desc() {
		return tp_desc;
	}
	public void setTp_desc(String tp_desc) {
		this.tp_desc = tp_desc;
	}
	public String getTp_bigImgurl() {
		return tp_bigImgurl;
	}
	public void setTp_bigImgurl(String tp_bigImgurl) {
		this.tp_bigImgurl = tp_bigImgurl;
	}
	public String getTp_smallImgurl() {
		return tp_smallImgurl;
	}
	public void setTp_smallImgurl(String tp_smallImgurl) {
		this.tp_smallImgurl = tp_smallImgurl;
	}
	@Override
	public String toString() {
		return "MeiJingBean [tp_id=" + tp_id + ", tp_name=" + tp_name
				+ ", tp_rq=" + tp_rq + ", tp_desc=" + tp_desc
				+ ", tp_bigImgurl=" + tp_bigImgurl + ", tp_smallImgurl="
				+ tp_smallImgurl + "]";
	}
}
