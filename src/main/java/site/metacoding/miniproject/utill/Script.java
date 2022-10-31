package site.metacoding.miniproject.utill;

public class Script {
	
	public static String back(String msg) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('"+msg+"');");
		sb.append("history.back();");
		sb.append("</script>");
		return sb.toString();
	}
	
	public static String href(String url) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("location.href='"+url+"';");
		sb.append("</script>");
		return sb.toString();
	}
	
	public static String href(String url, String msg) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('"+msg+"');");
		sb.append("history.back('"+url+"');");
		sb.append("</script>");
		return sb.toString();
	}
	
	public static String valReturn(String msg) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('"+msg+"');");
		sb.append("</script>");
		return sb.toString();
	}
}