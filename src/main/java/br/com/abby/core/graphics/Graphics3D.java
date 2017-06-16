package br.com.abby.core.graphics;

import br.com.abby.linear.AimPoint;
import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.linear.Point3D;

import com.badlogic.gdx.math.Vector3;

public interface Graphics3D extends Graphics {
	
	//Drawing Methods
	void drawLine(Point3D a, Point3D b);
	
	void drawLine(Point3D a, Vector3 b);
	
	void drawLine(Vector3 a, Vector3 b);
	
	void drawSphere(Vector3 point, double radius);
	
	void drawSphere(AimPoint point, double radius);

	//GL Methods
	void glClear(int mask);

	void glLoadIdentity();
	
	int[] getViewPort();
	
	void glMatrixMode(int mode);
	
}
