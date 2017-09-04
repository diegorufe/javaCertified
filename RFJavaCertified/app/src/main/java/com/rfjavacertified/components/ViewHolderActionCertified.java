package com.rfjavacertified.components;

import android.view.View;
import android.widget.TextView;

import com.rfjavacertified.R;
import com.rfjavacertified.beans.ItemListCerfied;

import core.media.rf.com.rfmediacore.components.recicleview.ViewHolderAction;
import core.media.rf.com.rfmediacore.entity.RFBaseEntity;

/**
 * Created by diego on 04/09/2017.
 */

public class ViewHolderActionCertified extends ViewHolderAction {

    TextView descripcion;

    public ViewHolderActionCertified(View view) {
        super(view);
    }

    @Override
    public void initDataLatout() {
        if (this.getView() != null) {
            descripcion = (TextView) getView().findViewById(R.id.item_descri_certified);
        }
    }

    @Override
    public void actionView(RFBaseEntity item) {

    }

    @Override
    public void actionView(Object item) {
        initDataLatout();
        if (item != null && descripcion != null) {
            super.actionView(item);
            ItemListCerfied imteList = (ItemListCerfied) item;
            descripcion.setText(imteList.getName() == null ? "" : imteList.getName());
        }
    }
}
