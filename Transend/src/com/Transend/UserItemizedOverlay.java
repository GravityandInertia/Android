package com.Transend;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: brandon
 * Date: 11/20/11
 * Time: 7:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserItemizedOverlay extends ItemizedOverlay {

    //Declaring class variables
    private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
    private Context mContext;
    private Boolean mBoolean = false;
    private Dialog.OnClickListener mListener;

    @Override
    protected OverlayItem createItem(int i) {
       return mOverlays.get(i);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int size() {
        return mOverlays.size();  //To change body of implemented methods use File | Settings | File Templates.
    }

    //Constructor
    public UserItemizedOverlay(Drawable defaultMarker) {
        super(boundCenterBottom(defaultMarker));
    }

    public void addOverlay(OverlayItem overlay) {
        mOverlays.add(overlay);
        populate();
    }


    public UserItemizedOverlay(Drawable defaultMarker, Context context) {
        super(boundCenterBottom(defaultMarker));
        mContext = context;
    }

    //@Override
    protected boolean onTap(int index) {
        OverlayItem item = mOverlays.get(index);
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);

        dialog.setTitle(item.getTitle());
        dialog.setMessage(item.getSnippet());

        //Setting the positive button dialog
        dialog.setPositiveButton("See Profile", mListener);

        dialog.show();
        return true;
    }


    public void setOnClickListener(DialogInterface.OnClickListener listener) {
        mListener = listener;
    }
}
