package demo;
import javax.annotation.Resource;

import org.junit.Test;

import healthArchive.Bo_vds_unified;

public class TestCase extends BaseTest {

	@Resource
	private Bo_vds_unified boVdsUnified;
	
	@Test
	public void test() {
		System.out.println(boVdsUnified.getPdData("sdf"));
	}
	
}
