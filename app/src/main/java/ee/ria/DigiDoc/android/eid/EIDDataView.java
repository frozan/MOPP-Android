package ee.ria.DigiDoc.android.eid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import ee.ria.DigiDoc.R;
import ee.ria.DigiDoc.android.Application;
import ee.ria.DigiDoc.android.model.EIDData;
import ee.ria.DigiDoc.android.model.idcard.IdCardData;
import ee.ria.DigiDoc.android.utils.Formatter;

public final class EIDDataView extends LinearLayout {

    private final TextView typeView;
    private final TextView givenNamesView;
    private final TextView surnameView;
    private final TextView personalCodeView;
    private final TextView citizenshipView;
    private final View documentNumberLabelView;
    private final TextView documentNumberView;
    private final View expiryDateLabelView;
    private final TextView expiryDateView;

    private final Formatter formatter;

    public EIDDataView(Context context) {
        this(context, null);
    }

    public EIDDataView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EIDDataView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public EIDDataView(Context context, @Nullable AttributeSet attrs, int defStyleAttr,
                       int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setOrientation(VERTICAL);
        inflate(context, R.layout.eid_home_data, this);
        typeView = findViewById(R.id.eidHomeDataType);
        givenNamesView = findViewById(R.id.eidHomeDataGivenNames);
        surnameView = findViewById(R.id.eidHomeDataSurname);
        personalCodeView = findViewById(R.id.eidHomeDataPersonalCode);
        citizenshipView = findViewById(R.id.eidHomeDataCitizenship);
        documentNumberLabelView = findViewById(R.id.eidHomeDataDocumentNumberLabel);
        documentNumberView = findViewById(R.id.eidHomeDataDocumentNumber);
        expiryDateLabelView = findViewById(R.id.eidHomeDataExpiryDateLabel);
        expiryDateView = findViewById(R.id.eidHomeDataExpiryDate);

        formatter = Application.component(context).formatter();
    }

    public void setData(@NonNull EIDData data) {
        typeView.setText(formatter.eidType(data.type()));
        givenNamesView.setText(data.givenNames());
        surnameView.setText(data.surname());
        personalCodeView.setText(data.personalCode());
        citizenshipView.setText(data.citizenship());
        if (data instanceof IdCardData) {
            IdCardData idCardData = (IdCardData) data;
            documentNumberView.setText(idCardData.documentNumber());
            expiryDateView.setText(formatter.idCardExpiryDate(idCardData.expiryDate()));
            documentNumberLabelView.setVisibility(VISIBLE);
            documentNumberView.setVisibility(VISIBLE);
            expiryDateLabelView.setVisibility(VISIBLE);
            expiryDateView.setVisibility(VISIBLE);
        } else {
            documentNumberLabelView.setVisibility(GONE);
            documentNumberView.setVisibility(GONE);
            expiryDateLabelView.setVisibility(GONE);
            expiryDateView.setVisibility(GONE);
        }
    }
}