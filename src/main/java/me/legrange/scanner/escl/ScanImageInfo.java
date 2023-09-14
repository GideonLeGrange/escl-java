package me.legrange.scanner.escl;

import org.simpleframework.xml.Element;

public final class ScanImageInfo {

    @Element(name = "JobUri")
    private String jobUri;
    @Element(name = "JobUuid")
    private String jobUuid;

    @Element(name="ActualWidth", required = true)
    private int actualWidth;
    @Element(name="ActualHeight", required = true)
    private int actualHeight;
    @Element(name="BytesPerLine", required = true)
    private int bytesPerLine;

    @Override
    public String toString() {
        return "ScanImageInfo{" +
                "jobUri='" + jobUri + '\'' +
                ", jobUuid='" + jobUuid + '\'' +
                ", actualWidth=" + actualWidth +
                ", actualHeight=" + actualHeight +
                ", bytesPerLine=" + bytesPerLine +
                '}';
    }

    /*

<scan:ScanImageInfo
xsi:schemaLocation="http://schemas.hp.com/imaging/escl/20
11/05/03 eSCL.xsd"
xmlns:scan="http://schemas.hp.com/imaging/escl/2011/05/03
" xmlns:pwg="http://www.pwg.org/schemas/2010/12/sm"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <pwg:JobUri>/ScanJobs/893e6fcd-487f-4056-a8c9-
a87709b85daf</pwg:JobUri>
  <pwg:JobUuid>893e6fcd-487f-4056-a8c9-
a87709b85daf</pwg:JobUuid>
  <scan:ActualWidth>2000</scan:ActualPageWidth>
  <scan:ActualHeight>3000</scan:ActualPageHeight>
  <scan:ActualBytesPerLine>2000</scan:ActualBytesPerLine>
</scan:ScanImageInfo>
     */
}
