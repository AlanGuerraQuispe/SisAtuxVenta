package com.aw.core.report.sun;
/** This sample code is to demonstrate Java Print Service API
  * JpsServlet.java
  * Date: May 2002
  * Author: Rajesh Ramchandani
  *         Ck Prasad
 */

import javax.print.*;
import javax.print.attribute.Attribute;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class Print {

                private String chromaticity = "Monochrome";
                private String copies = "1";
                private String destination = "/file.out";
                private String fidelity = "false";
                private String jobname = "Java printing"; 
                private String jobsheets = "standard";
                private String media = "iso-a4";
                private static String filetypestr = "text"; 
                private String filename = "file.txt"; 
		private String orientation = "landscape";
		private String sides = "duplex";
		private String mediasize = "A4";
		private static boolean useServiceFormat = false;
		private boolean printable = false;
		private boolean pageable = false;
		private boolean renderable_image = false;
		Properties prop = new Properties();
		String compromised[] = new String[10]; 
		static PrintRequestAttributeSet aset;
		static int compi = 0;
		Attribute[] comprom_attr = new Attribute[20];
		private String newl  = System.getProperty("line.separator");
		PrintService[] services;
		DocFlavor myFlavor; 
		FileInputStream fin;
		Doc myDoc;
		PrintRequestAttributeSet set;

	/**
	 * Gets the document's requested attributes.
	 * Looks for .props file in the current directory and throws 
	 * FileNotFoundException if not found.
	 * @param	none
	 * @return	none
	 * @throws	FileNotFoundException
	 */
	public void getDocProperies() {
		//Properties prop = new Properties();
		try {
		FileInputStream in = new FileInputStream(".props");
		prop.load(in);
		
		//This Enum gives list of all Properties
/*
		for(Enumeration enum = prop.propertyNames(); enum.hasMoreElements() ;) {
			System.out.println(enum.nextElement());
		}
*/

		chromaticity = prop.getProperty("CHROMATICITY");
		copies = prop.getProperty("COPIES");
		destination = prop.getProperty("DESTINATION");
		fidelity = prop.getProperty("FIDELITY");
		jobname = prop.getProperty("JOBNAME");
		jobsheets = prop.getProperty("JOBSHEETS");
		media = prop.getProperty("MEDIA");
		mediasize = prop.getProperty("MEDIASIZE");
		filetypestr = prop.getProperty("FILETYPE");
		filename = prop.getProperty("FILENAME");
		orientation = prop.getProperty("ORIENTATION");
		sides = prop.getProperty("SIDES");
		} catch(Exception e) { 
			System.out.println("Props file not found or:" + e.getMessage()); 
		}
	}

	/**
	 * Check for the supported file types
	 * File type is important for proper printing.
	 * @param None
	 * @return true or false
	 */
	public boolean isSupportedFileType(String lfiletypestr) {
		System.out.println("File Type is " + filetypestr);

		filetypestr = lfiletypestr;

		if (filetypestr != null ) {
		if(filetypestr.equalsIgnoreCase("unknown")) {
			useServiceFormat = true;
			return true;
		}
		if(filetypestr.equalsIgnoreCase( "text") || filetypestr.equalsIgnoreCase( "GIF") || filetypestr.equalsIgnoreCase( "JPEG") ||
			filetypestr.equalsIgnoreCase( "PCL") || filetypestr.equalsIgnoreCase( "PDF") || filetypestr.equalsIgnoreCase( "POSTSCRIPT") || filetypestr.equalsIgnoreCase( "HTML") ) {
			useServiceFormat = false;
			return true;
		} 
		}
		return false;
	}

			
	/**
	 * Loads the file to print
	 * This method doesn't check for correctness of file Type specified
	 * Just loads the file from Input stream
	 * @param	file name
	 * @return 	void
	 */
	public void loadFile(String lfilename) {
		filename = lfilename;
		try {
                	fin = new FileInputStream(filename);
         		if (fin == null) {
                		return;
         		}
		} catch(FileNotFoundException ffne) {
               		System.out.println("File not found " + filename);
			ffne.printStackTrace();
               		System.exit(1);
        	}
	}


	/**
  	 *Discover the  default printer Service
	 */
	public PrintService getDefaultPrintService() {
		PrintService defservices = PrintServiceLookup.lookupDefaultPrintService();
                System.out.println("Default Print service is " + defservices);
		return defservices;
	}


	/**
	 * List all the services
	 * @param	none
	 * @return	Array of Services
	 */
	public PrintService[] listPrinters() {
		services = PrintServiceLookup.lookupPrintServices(myFlavor, aset);
                System.out.println("No. of print Services Found is    : " + services.length);
		return services;
	}


	/**
	 *Discovers the printers that can print with requested attribute set 
	 */
	public PrintService[] getPrintServices() {

	        //Before sending the request for lookup, list the Attribute set here
            Attribute[] nattr = aset.toArray();
	    int loop = nattr.length;
/*          System.out.println("New Attributes set:");
            for (int i = 0; i < nattr.length; i++) {
                System.out.println("test " + nattr[i]);
            }
            System.out.println(newl);
*/

	
		for(int i = 0; i <= loop; i++ ) {
            		nattr = aset.toArray();
            		for (int k = 0; k < nattr.length; k++) {
 //               		System.out.println("attribute " + nattr[k]);
            		}

			services = PrintServiceLookup.lookupPrintServices(myFlavor, aset);
			if(services.length > 0 ) {
				break;
			}else {
				System.out.println(" No Print Services found, will compromise attribute");
				compromiseAttribute(i); //remove one attribute
			} 
		}

		for (int ck = 0; ck < compromised.length; ck++ ) {
			if (compromised[ck] != null ) {
				System.out.println(compromised[ck]);
			}
		}
        	// Get the supported PrintServiceatribute categories that we can specify
        	for(int j = 0; j < services.length; j++ ) {
                	Class [] psattribute = services[j].getSupportedAttributeCategories();
                	System.out.println("Printer name is: " + services[j].getName());
                	System.out.println("                            ");
                	//System.out.println("No. of Print Service attribute categories are " + psattribute.length);
                	System.out.println("Supported Print Service attribute categories are: " );
                	for(int i = 0; i < psattribute.length; i++ ) {
                        	System.out.println(psattribute[i]);
                        	System.out.print("      ");

                        	Object value = services[j].getSupportedAttributeValues(psattribute[i],null,null);

                                if(value == null) {
                                        break;
                                }
                                if (value.getClass().isArray()) {
                                        Object[] v = (Object[])value;
                                        for (int k=0; k<v.length; k++) {
                                                if (k > 0) {
                                                        System.out.print(", ");
                                                        System.out.print(v[k]);

						}
                                        }
                                }
                                else {
                                System.out.print(value);
                                System.out.println();
				}
                  }

        	}

	return services;
	}

	/**
	 * Creates a Print Job from one of the Print Services
	 * @param	printservice (usually returned from getPrintServices()
	 * @return 	DocprintJob
	 */
	public DocPrintJob createDocPrintJob(PrintService ps) {
		DocPrintJob job = ps.createPrintJob();
		return job;
	}

	/**
	 * Prints the Print Job to the specified printService
	 * @param	DocPrintJob
	 *		Doc
	 * @return	void
	 */
	public static void printJob(DocPrintJob dpj, Doc d) {
		DocPrintJob ljob = dpj;
		Doc ld = d;
		try {
			System.out.println("Firing the job.............>>");
			System.out.println("Doc is :" +ld);
			if (ld != null ) {
			DocFlavor df = ld.getDocFlavor();
			if (df != null) {
				System.out.println("DocFlavor is :" + df.toString());
			}
			}
			/*DocAttributeSet das = ld.getAttributes();
			Attribute[] da = das.toArray();
			for (int i=0; i< da.length; i++) {
				System.out.println("Attributes from Doc is :" + da[i].getName());
			}
			*/
			
			ljob.print(ld,aset);
			System.out.println("printed ljob");
			//Thread.sleep(100000);
			
		}catch(PrintException pe) {
			System.out.println("Got Printer Exception pe:" +pe.getMessage());
			System.out.println("Cause of pe is " + pe.getCause());
			pe.printStackTrace();
		}
		catch(Exception sl) {
			System.out.println("Cause of sl is " + sl.getCause());
			sl.printStackTrace();
			System.out.println("Got Printer Exception sl:" +sl.getMessage());

		}
	}

	
	/**
	 * Creates the request for print service with specified attributes
	 */
	public void setRequest() {
		aset = new HashPrintRequestAttributeSet();

		/* Set Chromaticity */
		if (chromaticity != null ) {
		if(chromaticity.equalsIgnoreCase( "monochrome")) {
			aset.add(Chromaticity.MONOCHROME);
		}
		if(chromaticity.equalsIgnoreCase( "color")) {
			aset.add(Chromaticity.COLOR);
		}
		} else {
                aset.add(Chromaticity.MONOCHROME);
		}
		
		/* Set number of Copies */
		if(copies == null ) {
			aset.add(new Copies(1));
		}
		if (copies != null ) {
			aset.add(new Copies(Integer.parseInt(copies)));
		}

		/* Set Fidelity */
		if(fidelity !=  null ) {

		if (fidelity.equalsIgnoreCase("false")) {
			aset.add(Fidelity.FIDELITY_FALSE);
		}
		if(fidelity.equalsIgnoreCase( "true")) {
			aset.add(Fidelity.FIDELITY_TRUE);
		}
		}

		/* Set number of sides of printing */
		if (sides != null ) {
		if(sides.equalsIgnoreCase("DUPLEX") || sides.equalsIgnoreCase("duplex") ) {
			aset.add(Sides.DUPLEX);
		}
		if(sides.equalsIgnoreCase("one_sided") || sides.equalsIgnoreCase("ONE_SIDED")) {
			aset.add(Sides.ONE_SIDED);
		} 
		} else {
		  aset.add(Sides.DUPLEX);
		}
		
		/* Set Media size, A4, letter or Transparency */
		if(media != null) {
		if (media.equalsIgnoreCase( "A4" )) {
			aset.add(MediaName.ISO_A4_WHITE);
		}
		if(media.equalsIgnoreCase( "Transparent")) {
			aset.add(MediaName.ISO_A4_TRANSPARENT);
		}
		if(media.equalsIgnoreCase( "Letter")) {
			aset.add(MediaName.NA_LETTER_WHITE);
		}
		}

		/* Set Orientation - landscape orportrait */
		if(orientation != null ) {
		if (orientation.equalsIgnoreCase( "landscape")) {
			aset.add(OrientationRequested.LANDSCAPE);
		}
		if(orientation.equalsIgnoreCase( "Portrait")) {
			aset.add(OrientationRequested.PORTRAIT);
		} 
		}
		if(jobname == null) {
			aset.add(new JobName("JPS Printing",null));
		}
		if (jobname != null ) {
		aset.add(new JobName(jobname,null)); 
		}
	}
		
 		
	/**
	 * Sets Document Flavor Type (DocFlavor) which will be used by
	 * PrintServiceLookup and also to create a Doc for Print Service.
	 * @param	none
	 * @return	void	
	 */
	 public void setDocFlavor() {
		if(useServiceFormat) {
			if(pageable) {
			myFlavor = DocFlavor.SERVICE_FORMATTED.PAGEABLE;
			}
			if(printable) {
                        myFlavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
                        }
			if(renderable_image) {
                        myFlavor = DocFlavor.SERVICE_FORMATTED.RENDERABLE_IMAGE;
                        }
		}
		if(filetypestr.equalsIgnoreCase( "text") || filetypestr.equalsIgnoreCase( "txt")) {
			myFlavor = DocFlavor.INPUT_STREAM.TEXT_PLAIN_US_ASCII;
		}
		if(filetypestr.equalsIgnoreCase( "html")) {
			System.out.println("setDocFlaovr is setting HTML....");
			myFlavor = DocFlavor.INPUT_STREAM.TEXT_HTML_US_ASCII ;
		}
		if(filetypestr.equalsIgnoreCase(  "GIF")) {
			myFlavor = DocFlavor.INPUT_STREAM.GIF;
		}
		if(filetypestr.equalsIgnoreCase( "JPEG")) {
			myFlavor = DocFlavor.INPUT_STREAM.JPEG;
		}
		if(filetypestr.equalsIgnoreCase( "PCL")) {
			myFlavor = DocFlavor.INPUT_STREAM.PCL;
		}
		if(filetypestr.equalsIgnoreCase( "PDF")) {
			System.out.println("setDocFlaovr is setting PDF....");
			myFlavor = DocFlavor.INPUT_STREAM.PDF;
		
		}
	}
	

	/**
	 * Create a Doc for printing. Will use SimpleDoc for now
	 * @param	none
	 * @return	Doc
	 */
	public Doc createFinalDoc() {
		System.out.println(" parameters are : "+ fin + myFlavor );
		myDoc = new SimpleDoc(fin, myFlavor, null);
		return myDoc;
	}


	/**
	 * Add PrintAttributeListener to the service
	 * This monitors the changes in Print Service Attributes
	 * @param	Print service
	 * @return	void
	 */
	public void addPrintAListener(PrintService service) {
		PrintAListener pAListener = new PrintAListener();
		service.addPrintServiceAttributeListener(pAListener);
	}

	/**
	 * Add PrintAttributeListener to the all Print services
	 * This monitors the changes in Print Service Attributes
	 * @param	array of print services
	 * @return	void
	 */
	public void addPrintAListener(PrintService[] service) {
		PrintAListener pAListener = new PrintAListener();
		for(int i = 0; i < service.length; i++) {
			System.out.println("Adding AListener to service number: " + i);
			service[i].addPrintServiceAttributeListener(pAListener);
		}
	}

	/**
	 * Add printJobListeners to monitor the print job status
	 * This monitors the changes in Print job status
	 * @param	Print Job
	 * @return	void
	 */
	public void addPrintJListener(DocPrintJob job) {
		PrintJListener pjListener = new PrintJListener();
		job.addPrintJobListener(pjListener);
	}


	/**
	 *Removes the requested attributes from the set to get atleast one Print Service
	 * @param	none
	 * @return	none
	 */
	public void compromiseAttribute(int n) {
		int i = 0;
		//System.out.println("Inside compromiseAttribute() and n is " + n);
		Attribute[] newattr = aset.toArray();
		if (newattr != null && newattr.length > 0 ) {
			//System.out.println("NewAttribute length " + newattr.length);
			//System.out.println("Attribute is " + newattr[i].getName());
			Class compromiseCategory = newattr[i].getCategory();
			//System.out.println("Category is " + compromiseCategory.getName());
			compromised[i] = compromiseCategory.getName();
                       	aset.remove(newattr[i]);
		}
	}

	/**
	 *Returns the name of compromised attribute
	 * @param	none
	 * @return	compromised attribute string
	 */
	public String[] getCompromised() {
	 	if (compromised != null ) 	{
			return compromised;
		} else return null;
	}

	/**
	 *returns a array of printServices
	 * @param	none
	 * @return	PrintService[]
	 */
	public PrintService[] getServices() {
		return services;
	}


	//Testing the Print application

	public static void main(String args[]) {
		Print p = new Print(); //Create a Print object
		p.getDocProperies(); //Get Document properties from a .props file
		p.loadFile("d:/file.txt"); //Load the file using inputstream
		p.setRequest(); //All Attributes are in place
		if(!(p.isSupportedFileType(filetypestr))) {
			System.out.println("File Type is not supported");
			System.exit(1);
		}
		p.setDocFlavor(); //Calling this method sets the appropriate DocFlavor
		PrintService[] ps = p.getPrintServices(); //Returns a list of Print Services
		if(ps.length == 0) {
			System.out.println("No printers found, exiting!");
			System.exit(1);
		}
		DocFlavor[] sdf = ps[0].getSupportedDocFlavors();
		System.out.println("Supported DocFlavors are:   ");
		for( int r = 0; r < sdf.length; r++ ) {
			System.out.println(sdf[r].toString());
		}
		p.addPrintAListener(ps);
		PrintService[] plist = p.listPrinters(); //List of All print Services available
		System.out.println("Printers are: ");
		for(int i=0; i < plist.length; i++ ) {
			System.out.println(plist[i]);
		}
		PrintService defp = p.getDefaultPrintService(); //Default Print Service if you want to use
		System.out.println("Default printer is " + defp.getName() );
		DocPrintJob pj = p.createDocPrintJob(ps[0]);
		p.addPrintJListener(pj);
		Doc d = p.createFinalDoc();
		printJob(pj,d);
		
	}

	
}
