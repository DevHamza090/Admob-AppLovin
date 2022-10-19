# MultiRecyclerViewNativeAdmobWithSpanLookup
## Example For Java And Kotlin.
<img src="https://github.com/Delecom/MultiRecyclerViewNativeAdmob/blob/master/app/Screenshot_20221018-123414.png" width="250" height="444" />,    <img src="https://github.com/Delecom/MultiRecyclerViewNativeAdmob/blob/master/app/Screenshot_20221018-123545.png" width="250" height="444" />

## Source code
This is the Adapter class of `RecyclerView`. This class is extended from `RecyclerView.Adapter<RecyclerView.ViewHolder>()` class.
### Adapter of RecyclerView

### Kotlin
```
class KotlinAdapter(val activity: Activity, val kotlinArrayList: ArrayList<KotlinDataClass>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(activity)
        return if (viewType == ITEM_VIEW) {
            val view: View = layoutInflater.inflate(R.layout.adapter_item, parent, false)
            MainViewHolder(view)
        } else (if (viewType == AD_VIEW) {
            val view: View = layoutInflater.inflate(R.layout.native_ad_container, parent, false)
            AdViewHolder(view)
        } else {
            null
        })!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == ITEM_VIEW) {
            val pos =
                (position - Math.round((position / ITEM_FEED_COUNT).toFloat()))
            (holder as MainViewHolder).bindData(kotlinArrayList[pos])
        } else if (holder.itemViewType == AD_VIEW) {
            (holder as AdViewHolder).bindAdData()
        }
    }

    override fun getItemCount(): Int {
        return if (kotlinArrayList.size > 0) {
            kotlinArrayList.size + Math.round((kotlinArrayList.size / ITEM_FEED_COUNT).toFloat())
        } else kotlinArrayList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if ((position + 1) % ITEM_FEED_COUNT == 0) {
            AD_VIEW
        } else ITEM_VIEW
    }


   

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: AdapterItemBinding

        init {
            binding = AdapterItemBinding.bind(itemView)
        }

        fun bindData(kotlinDataClass: KotlinDataClass) {
            binding.tvName.text = kotlinDataClass.name
            val format = SimpleDateFormat("MM/dd/yyyy")
            val date = format.format(kotlinDataClass.date)
            binding.tvDate.text = date
        }
    }

    inner class AdViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: NativeAdContainerBinding

        init {
            binding = NativeAdContainerBinding.bind(itemView)
        }

        fun bindAdData() {
            Load the Native Ad
        }
    }

    companion object {
        const val ITEM_VIEW = 0
        const val AD_VIEW = 1
        const val ITEM_FEED_COUNT = 9
    }
}
```
### Java
```
public class JavaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int ITEM_VIEW = 0;
    public static final int AD_VIEW = 1;
    public static final int ITEM_FEED_COUNT = 9;
    public final Activity activity;
    public final ArrayList<JavaDataClass> javaArrayList;

    public JavaAdapter(Activity activity, ArrayList<JavaDataClass> javaArrayList) {
        this.activity = activity;
        this.javaArrayList = javaArrayList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        if (viewType == ITEM_VIEW) {
            View view = layoutInflater.inflate(R.layout.adapter_item, parent, false);
            return new MainViewHolder(view);
        } else if (viewType == AD_VIEW) {
            View view = layoutInflater.inflate(R.layout.native_ad_container, parent, false);
            return new AdViewHolder(view);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == ITEM_VIEW) {
            int pos = position - Math.round(position / ITEM_FEED_COUNT);
            ((MainViewHolder) holder).bindData(javaArrayList.get(pos));
        } else if (holder.getItemViewType() == AD_VIEW) {
            ((AdViewHolder) holder).bindAdData();
        }
    }

    @Override
    public int getItemCount() {
        if (javaArrayList.size() > 0) {
            return javaArrayList.size() + Math.round(javaArrayList.size() / ITEM_FEED_COUNT);
        }
        return javaArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if ((position + 1) % ITEM_FEED_COUNT == 0) {
            return AD_VIEW;
        }
        return ITEM_VIEW;
    }

  

    public class MainViewHolder extends RecyclerView.ViewHolder {

        AdapterItemBinding binding;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = AdapterItemBinding.bind(itemView);
        }


        private void bindData(JavaDataClass main) {
            binding.tvName.setText(main.getName());

            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            String date = format.format(main.getDate());
            binding.tvDate.setText(date);
        }
    }

    public class AdViewHolder extends RecyclerView.ViewHolder {

        NativeAdContainerBinding binding;

        public AdViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = NativeAdContainerBinding.bind(itemView);
        }

        private void bindAdData() {
        
          Load the Native Ad
           
        }
    }


}

```
