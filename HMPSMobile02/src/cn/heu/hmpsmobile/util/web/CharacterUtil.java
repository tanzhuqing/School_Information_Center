package cn.heu.hmpsmobile.util.web;

import org.apache.commons.lang.RandomStringUtils;

public class CharacterUtil
{
	public static String randomString(int length)
	{
		return RandomStringUtils.randomAlphabetic(length);
	}
}
