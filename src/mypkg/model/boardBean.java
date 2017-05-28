package mypkg.model;

public class boardBean {
	private int seq;
	private String subject;
	private String writer;
	private String wDate;
	private String contents;
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getwDate() {
		return wDate;
	}
	public void setwDate(String wDate) {
		this.wDate = wDate;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	@Override
	public String toString() {
		return "boardBean [seq=" + seq + ", subject=" + subject + ", writer="
				+ writer + ", wDate=" + wDate + ", contents=" + contents + "]";
	}
	
	
}
