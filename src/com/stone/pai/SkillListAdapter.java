package com.stone.pai;

import butterknife.ButterKnife;
import butterknife.InjectView;

import com.stone.pai.bean.Skill;
import com.stone.pai.ui.NonScrollableGridView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class SkillListAdapter extends ArrayAdapter<Skill> {
	private final LayoutInflater mInflater;
	
	public SkillListAdapter(Context context) {
		super(context, R.layout.fragment_category);
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        
        if (convertView == null) {
        	convertView = mInflater.inflate(R.layout.fragment_category, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        
        Skill skill = this.getItem(position);
        holder.skillName.setText(skill.getName());
        holder.subSkill.setAdapter(new ArrayAdapter<Skill>(
        		this.getContext(),
        		R.layout.fragment_category_item,
        		R.id.textViewSkillName,
        		skill.getChildren()
        		));
        
        return convertView;
    }
    
    static class ViewHolder {
        @InjectView(R.id.textViewMaster) TextView skillName;
        //@InjectView(R.id.gridViewCategory) 
        public NonScrollableGridView subSkill;

        public ViewHolder(View view) {
          ButterKnife.inject(this, view);
          subSkill = (NonScrollableGridView)view.findViewById(R.id.gridViewCategory);
        }
    }
}
