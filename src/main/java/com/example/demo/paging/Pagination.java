package com.example.demo.paging;

public class Pagination {
	
	/** 1. ページに表示される最大リスト数 **/
	private int pageSize = 3;

	/** 2. ページングブロックのボタン数 **/
	private int blockSize = 3;

	/** 3. 現在ページ **/
	private int page = 1;

	/** 4. 現在ブロック **/
	private int block = 1;

	/** 5.全リスト数 **/
	private int totalListCnt;

	/** 6. 全ページ数 **/
	private int totalPageCnt;

	/** 7. 全ブロック数 **/
	private int totalBlockCnt;

	/** 8. ブロックの開始のページ **/
	private int startPage = 1;

	/** 9. ブロックの最終のページ **/
	private int endPage = 1;

	/** 10. DBインデックス **/
	private int startIndex = 0;

	/** 11. 前のブロックの最後のページ **/
	private int prevBlock;

	/** 12. 次のブロックの最初のページ **/
	private int nextBlock;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getBlock() {
		return block;
	}

	public void setBlock(int block) {
		this.block = block;
	}

	public int getTotalListCnt() {
		return totalListCnt;
	}

	public void setTotalListCnt(int totalListCnt) {
		this.totalListCnt = totalListCnt;
	}

	public int getTotalPageCnt() {
		return totalPageCnt;
	}

	public void setTotalPageCnt(int totalPageCnt) {
		this.totalPageCnt = totalPageCnt;
	}

	public int getTotalBlockCnt() {
		return totalBlockCnt;
	}

	public void setTotalBlockCnt(int totalBlockCnt) {
		this.totalBlockCnt = totalBlockCnt;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getPrevBlock() {
		return prevBlock;
	}

	public void setPrevBlock(int prevBlock) {
		this.prevBlock = prevBlock;
	}

	public int getNextBlock() {
		return nextBlock;
	}

	public void setNextBlock(int nextBlock) {
		this.nextBlock = nextBlock;
	}
	
	public Pagination(int totalListCnt, int page) {
	
		// totalListCntとpageをControllerから受ける
		
		setPage(page);
		
		setTotalListCnt(totalListCnt);
		if(totalListCnt == 0) {
			setEndPage(1);
			setPage(1);
			setTotalPageCnt(1);
			return;
		}
	
		/** 6. 全ページ数 **/
		setTotalPageCnt((int) Math.ceil(totalListCnt * 1.0 / pageSize));
	
		/** 7. 全ブロック数 **/
		setTotalBlockCnt((int) Math.ceil(totalPageCnt * 1.0 / blockSize));
	    
		/** 4. 現在ブロック **/
		setBlock((int) Math.ceil((page * 1.0)/blockSize)); 
	
		/** 8. ブロックの開始のページ **/
		setStartPage((block - 1) * blockSize + 1);
	    
		/** 9. ブロックの最終のページ **/
		setEndPage(startPage + blockSize - 1);
		/* === ブロックの最終のページのvalidation ===*/
		if(endPage > totalPageCnt){this.endPage = totalPageCnt;}
	    
		/** 11. 前のブロックの最後のページ **/
		setPrevBlock((block * blockSize) - blockSize);
		/* === 前のブロックのvalidation === */
		if(prevBlock < 1) {this.prevBlock = 1;}
	
		/** 12. 次のブロックの最初のページ **/
		setNextBlock((block * blockSize) + 1);
		/* === 次のブロックのvalidation ===*/
		if(nextBlock > totalPageCnt) {nextBlock = totalPageCnt;}
	    
		/** 10. DBインデックス **/
		setStartIndex((page-1) * pageSize);

	}
}
