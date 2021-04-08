package com.jetwiz.admin.model

data class ModelCategories(
	val categories: List<CategoriesItem> = mutableListOf()
)

data class CategoriesItem(
	val strCategory: String? = null,
	val strCategoryDescription: String? = null,
	val idCategory: String? = null,
	val strCategoryThumb: String? = null
)

