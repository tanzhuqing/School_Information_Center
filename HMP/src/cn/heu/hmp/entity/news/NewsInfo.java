package cn.heu.hmp.entity.news;

import java.io.Serializable;

public class NewsInfo implements Serializable{
	

		private static final long serialVersionUID = 1L;
		//±‰¡ø
		private String new_ID;
		private String new_title;
		private String new_desc;
		private String new_imgurl;
		private String new_fbr;
		private String new_fbrq;
		private String new_content;
		private boolean new_isimgnews=false;
		// set get 
		
		public String getNew_ID() {
			return new_ID;
		}
		
		public boolean isNew_isimgnews() {
			return new_isimgnews;
		}

		public void setNew_isimgnews(boolean new_isimgnews) {
			this.new_isimgnews = new_isimgnews;
		}

		public String getNew_content() {
			return new_content;
		}
		public void setNew_content(String new_content) {
			this.new_content = new_content;
		}
		public String getNew_fbr() {
			return new_fbr;
		}
		public void setNew_fbr(String new_fbr) {
			this.new_fbr = new_fbr;
		}
		public String getNew_fbrq() {
			return new_fbrq;
		}
		public void setNew_fbrq(String new_fbrq) {
			this.new_fbrq = new_fbrq;
		}

		public String getNew_imgurl() {
			return new_imgurl;
		}
		public void setNew_imgurl(String new_imgurl) {
			this.new_imgurl = new_imgurl;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		public void setNew_ID(String new_ID) {
			this.new_ID = new_ID;
		}
		public String getNew_title() {
			return new_title;
		}
		public void setNew_title(String new_title) {
			this.new_title = new_title;
		}
		public String getNew_desc() {
			return new_desc;
		}
		public void setNew_desc(String new_desc) {
			this.new_desc = new_desc;
		}
		@Override
		public String toString() {
			return "NewsInfo [new_ID=" + new_ID + ", new_title=" + new_title
					+ ", new_desc=" + new_desc + ", new_imgurl=" + new_imgurl
					+ ", new_fbr=" + new_fbr + ", new_fbrq=" + new_fbrq + "]";
		}

}
