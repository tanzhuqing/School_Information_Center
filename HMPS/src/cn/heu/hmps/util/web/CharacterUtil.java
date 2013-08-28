package cn.heu.hmps.util.web;

import org.apache.commons.lang.RandomStringUtils;

public class CharacterUtil
{
	public static String randomString(int length)
	{
		return RandomStringUtils.randomAlphabetic(length);
	}
}
