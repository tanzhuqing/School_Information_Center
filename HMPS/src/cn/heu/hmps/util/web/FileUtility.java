package cn.heu.hmps.util.web;

public class FileUtility
{
	// 返回随机生成的字符串名字
	public static String randomNameFile(String fileName)
	{
		if (-1 == fileName.lastIndexOf("."))
		{
			return CharacterUtil.randomString(5);
		}

		return CharacterUtil.randomString(5)
				+ fileName.substring(fileName.lastIndexOf("."), fileName
						.length());
	}

	// 获得文件的扩展名
	public static String getExtName(String fileName)
	{
		if (-1 == fileName.lastIndexOf("."))
		{
			return "";
		}

		return fileName.substring(fileName.lastIndexOf(".") + 1, fileName
				.length());
	}
}
