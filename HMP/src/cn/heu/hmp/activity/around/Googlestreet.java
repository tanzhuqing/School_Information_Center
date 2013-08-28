package cn.heu.hmp.activity.around;
import cn.heu.hmp.activity.R;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import java.util.List;     
 import android.graphics.drawable.Drawable;  
 import android.os.Bundle;  
import android.widget.Toast;

 import com.google.android.maps.GeoPoint;  
 import com.google.android.maps.MapActivity;  
 import com.google.android.maps.MapController;  
 import com.google.android.maps.MapView;  
 import com.google.android.maps.Overlay;  
import com.google.android.maps.OverlayItem;  
  public class Googlestreet extends MapActivity {  
    private MapView mapView;  
     @Override 

    public void onCreate(Bundle savedInstanceState) {  

       super.onCreate(savedInstanceState);  

         setContentView(R.layout.gmapstreet); 
     	 Bundle bundled=this.getIntent().getExtras();
     	 float lng=Float.parseFloat(bundled.getString("plon"));
    	 float lat=Float.parseFloat(bundled.getString("plat"));
         mapView = (MapView) findViewById(R.id.map_view); 
//         mapView.setTraffic(false);
//         mapView.setSatellite(false);
//         mapView.setStreetView(true);
         mapView.setBuiltInZoomControls(true);  
         List<Overlay> mapOverlays = mapView.getOverlays();  
         Drawable drawable = this.getResources().getDrawable(R.drawable.marker);  
         CustomItemizedOverlay itemizedOverlay =  new CustomItemizedOverlay(drawable, this);  
         GeoPoint point = new GeoPoint((int)(lat * 1E6), (int)(lng * 1E6)); 
         OverlayItem overlayitem =   
              new OverlayItem(point, "您好，这里是..", "哈尔滨工程大学");  
         itemizedOverlay.addOverlay(overlayitem);  
         mapOverlays.add(itemizedOverlay);  
         MapController mapController = mapView.getController();  
         mapController.animateTo(point);  
         mapController.setZoom(6);  
     }  

     @Override 

     protected boolean isRouteDisplayed() {  
         return false;  
     }     
		//使用Android Google Map Api之前必须检测系统中是否安装了Google map 应用，检测方法如下：网络googleIntent it = new Intent(
		//Intent.ACTION_VIEW, Uri.parse(
		//"http://ditu.google.cn/maps?hl=zh&mrt=loc&q="+weiduExtra+",
		///"+jingduExtra+""));
		//startActivity(it);
     protected boolean checkGoogleMap(){
    	 boolean  isInstallGMap = false;
    	      List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
    	 for (int i = 0; i < packs.size(); i++) {
    	 PackageInfo p = packs.get(i);
    	 if (p.versionName == null) {
    	      continue;
    	 }
    	 if ("com.google.android.apps.maps".equals(p.packageName)) {
    	     isInstallGMap = true;
    	     break;
    	 }
    	 }
    	 return isInstallGMap;
    	 }
 } 
