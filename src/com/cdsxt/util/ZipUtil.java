package com.cdsxt.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
	public static void zip(String path,String fileName) throws FileNotFoundException, IOException {
			File file = new File (path) ;
	         ZipOutputStream zos = new ZipOutputStream (
	                 new FileOutputStream (file.getParentFile().toString()+"/"+fileName+".zip" ) ) ;
	         // judge the file is the directory
	         if ( file.isDirectory ( ) )
	         {
            // get the every file in the directory
	           File [ ] files = file.listFiles ( ) ;
	 
	             for ( int i = 0 ; i < files.length ; i ++ )
	             {
	              // new the BuuferedInputStream
	                BufferedInputStream bis = new BufferedInputStream (
	                        new FileInputStream (
	                                 files [ i ] ) ) ;
	                 // the file entry ,set the file name in the zip
	                 // file
	                zos.putNextEntry ( new ZipEntry ( file
	                        .getName ( )
	                       + file.separator
	                        + files [ i ].getName ( ) ) ) ;
	                while ( true )
	                 {
	                     byte [ ] b = new byte [ 100 ] ;
	                     int len = bis.read ( b ) ;
	                   if ( len == - 1 )
	                         break ;
	                     zos.write ( b , 0 , len ) ;
	                 }
	
	                 // close the input stream
               bis.close ( ) ;
           }

	        }
        // close the zip output stream
	        zos.close ( ) ;
		}
	public static void unZip(String filename) throws FileNotFoundException, IOException {
		// get a zip file instance
		          File file = new File ( filename ) ;
		  
		         // get a ZipFile instance
		         ZipFile zipFile = new ZipFile ( file ) ;
		 
		         // create a ZipInputStream instance
		         ZipInputStream zis = new ZipInputStream ( new FileInputStream (
		                 file ) ) ;
		 
		         // create a ZipEntry instance , lay the every file from
		         // decompress file temporarily
		        ZipEntry entry = null ;
		 
		         // a circle to get every file
		        while ( ( entry = zis.getNextEntry ( ) ) != null )
		         {
		             System.out.println ( "decompress file :"
		                     + entry.getName ( ) ) ;
		 
		             // define the path to set the file
		             File outFile = new File ( file.getParent()+"/"
		                     + entry.getName ( ) ) ;
		
		            // if the file's parent directory wasn't exits ,than
		             // create the directory
	             if ( ! outFile.getParentFile ( ).exists ( ) )
	             {
		                 outFile.getParentFile ( ).mkdir ( ) ;
		             }
		
		             // if the file not exits ,than create the file
		             if ( ! outFile.exists ( ) )
		            {
		                 outFile.createNewFile ( ) ;
		             }
		 
		             // create an input stream
		             BufferedInputStream bis = new BufferedInputStream (
		                     zipFile.getInputStream ( entry ) ) ;
		 
		             // create an output stream
		             BufferedOutputStream bos = new BufferedOutputStream (
		                     new FileOutputStream ( outFile ) ) ;
		             byte [ ] b = new byte [ 100 ] ;
		             while ( true )
		             {
		                 int len = bis.read ( b ) ;
		                if ( len == - 1 )
		                     break ;
		                bos.write ( b , 0 , len ) ;
		           }
		             // close stream
		             bis.close ( ) ;
		             bos.close ( ) ;
		         }
		         zis.close ( ) ;

	}
}

