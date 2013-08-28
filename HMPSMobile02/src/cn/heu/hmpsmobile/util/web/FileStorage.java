package cn.heu.hmpsmobile.util.web;

import javax.servlet.http.HttpServletRequest;

import static java.io.File.separator;

public class FileStorage
{
	public static String FILE_STORAGE = "fileStorage";

	public static String DOCUMENT_ITEM_STORAGE = "documentItemStorage";

	//通用的返回路径的方法
	public static String getFileStorage(HttpServletRequest request, String desc)
	{
		return request.getSession().getServletContext().getRealPath(
				FILE_STORAGE + separator + desc);
	}

	public static String getDocumentItemStorage(HttpServletRequest request)
	{
		return getFileStorage(request, DOCUMENT_ITEM_STORAGE);
	}
}
