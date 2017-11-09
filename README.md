# PullRefreshDemo
使用MVCHelper和ultra库的demo

实现：
1、顶部自动加载
2、底部刷新
3、顶部可自定义


如：

        PtrClassicFrameLayout mPtrFrameLayout = (PtrClassicFrameLayout) findViewById(R.id.rotate_header_list_view_frame);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        MyHeader header = new MyHeader(this);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        mPtrFrameLayout.setLoadingMinTime(800);
        mPtrFrameLayout.setDurationToCloseHeader(800);
        mPtrFrameLayout.setHeaderView(header);
        mPtrFrameLayout.addPtrUIHandler(header);
        mPtrFrameLayout.setKeepHeaderWhenRefresh(true);//刷新时保持头部的显示，默认为true
        //mPtrFrame.disableWhenHorizontalMove(true);//如果是ViewPager，设置为true，会解决ViewPager滑动冲突问题。

        mvcHelper = new MVCUltraHelper<List<Book>>(mPtrFrameLayout);
        // 设置数据源
        mvcHelper.setDataSource(new BooksDataSource());
        // 设置适配器
        mvcHelper.setAdapter(new ReBooksAdapter(this));
        // 加载数据
        mvcHelper.refresh();
        
