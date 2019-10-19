package ayupov.ilgam.lesson006;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FactsVH extends RecyclerView.ViewHolder {

    private TextView tvFact;

    public FactsVH(@NonNull View itemView) {
        super(itemView);
        tvFact = itemView.findViewById(R.id.tvFact);
    }

    void onBind(Fact fact) {
        tvFact.setText(fact.getText());
    }
}
