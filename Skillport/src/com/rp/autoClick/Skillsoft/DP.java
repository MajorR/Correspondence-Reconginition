package com.rp.autoClick.Skillsoft;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import org.sikuli.script.Location;
import org.sikuli.script.Pattern;

public class DP {

	static int	REFERENCE_RES_WIDTH		= DP.getScreenWidth( );
	static int	REFERENCE_RES_HEIGHT	= DP.getScreenHeight( );


	public static BufferedImage getImg( final BufferedImage image,
			final int ref_width, final int ref_height ) {
		return DP.getScaledImage( image,
				DP.getX( image.getWidth( ), ref_width ),
				DP.getY( image.getHeight( ), ref_height ) );

	}

	public static Location getLoc( final int x, final int y ) {
		return DP.getLoc( x, y, DP.getScreenWidth( ), DP.getScreenHeight( ) );
	}

	public static Location getLoc( final int x, final int y,
			final Dimension reference ) {
		return DP.getLoc( x, y, reference.width, reference.height );
	}

	public static Location getLoc( final int x, final int y,
			final int ref_width, final int ref_height ) {
		return new Location( DP.getX( x, ref_width ), DP.getY( y, ref_height ) );
	}

	public static Location getLoc( final Location location ) {
		return DP.getLoc( location.x, location.y );
	}

	public static Location getLoc( final Location location,
			final Dimension reference ) {
		return DP.getLoc( location.x, location.y, reference.width,
				reference.height );
	}

	public static Pattern getPtrn( final Pattern pattern ) {
		return DP.getPtrn( pattern, DP.REFERENCE_RES_WIDTH,
				DP.REFERENCE_RES_HEIGHT );
	}

	public static Pattern getPtrn( final Pattern pattern, final int ref_width,
			final int ref_height ) {
		return new Pattern( DP.getImg( pattern.getBImage( ), ref_width,
				ref_height ) );
	}

	/**
	 * Resizes an image using a Graphics2D object backed by a BufferedImage.
	 * 
	 * @param srcImg
	 *            - source image to scale
	 * @param w
	 *            - desired width
	 * @param h
	 *            - desired height
	 * @return - the new resized image
	 */
	private static BufferedImage getScaledImage( final Image srcImg,
			final int w, final int h ) {
		final BufferedImage resizedImg = new BufferedImage( w, h,
				Transparency.TRANSLUCENT );
		final Graphics2D g2 = resizedImg.createGraphics( );
		g2.setRenderingHint( RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR );
		g2.drawImage( srcImg, 0, 0, w, h, null );
		g2.dispose( );

		System.out.println( "Original: " + srcImg.getWidth( null ) + ", "
				+ srcImg.getHeight( null ) );
		System.out.println( "Resized: " + resizedImg.getWidth( ) + ", "
				+ resizedImg.getHeight( ) );
		return resizedImg;
	}

	public static int getScreenHeight() {
		return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment( )
				.getDefaultScreenDevice( ).getDisplayMode( ).getHeight( );
	}

	public static int getScreenWidth() {
		return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment( )
				.getDefaultScreenDevice( ).getDisplayMode( ).getWidth( );
	}

	public static int getX( final int x ) {
		return DP.getX( x, DP.getScreenWidth( ) );
	}

	public static int getX( final int x, final Dimension reference ) {
		return DP.getX( x, reference.width );
	}

	public static int getX( final int x, final int ref_width ) {
		System.out.println( "X: " + x + "  : new X:"
				+ ( x * ( DP.getScreenWidth( ) / (float) ref_width ) ) );
		return (int) ( x * ( DP.getScreenWidth( ) / (float) ref_width ) );
	}

	public static int getY( final int y ) {
		return DP.getY( y, DP.getScreenHeight( ) );
	}

	public static int getY( final int y, final Dimension reference ) {
		return DP.getY( y, reference.height );
	}

	public static int getY( final int y, final int ref_height ) {
		return (int) ( y * ( DP.getScreenHeight( ) / (float) ref_height ) );
	}

	public static void setGlobalReferenceResolution( final int width,
			final int height ) {
		DP.REFERENCE_RES_HEIGHT = height;
		DP.REFERENCE_RES_WIDTH = width;
	}

	public DP( final Location location ) {
		// TODO Auto-generated constructor stub
	}

}
