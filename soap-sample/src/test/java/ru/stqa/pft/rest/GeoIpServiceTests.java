package ru.stqa.pft.rest;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

  @Test
  public void testMyIp(){
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("85.253.116.198");
    assertEquals(ipLocation, "<GeoIP><Country>EE</Country><State>01</State></GeoIP>");
  }

  @Test
  public void testInvalidIp(){
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("85.253.116.xxx");
    assertEquals(ipLocation, "<GeoIP><Country>EE</Country><State>01</State></GeoIP>");
  }
}
