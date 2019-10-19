package ayupov.ilgam.lesson006;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FactsAdapter extends RecyclerView.Adapter<FactsVH> {

    private List<Fact> facts;

    public FactsAdapter() {
        this.facts = new ArrayList<>();
    }

    @NonNull
    @Override
    public FactsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FactsVH(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_fact, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull FactsVH holder, int position) {
        holder.onBind(facts.get(position));
    }

    @Override
    public int getItemCount() {
        return facts.size();
    }

    public void setFacts(List<Fact> facts) {
        this.facts.addAll(facts);
        notifyDataSetChanged();
    }
}
