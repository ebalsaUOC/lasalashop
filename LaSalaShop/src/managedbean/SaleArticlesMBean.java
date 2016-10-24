package managedbean;

import java.util.*;

import javax.faces.model.SelectItem;
import javax.faces.bean.*;

import jpa.ArticlePrototypeJPA;
import jpa.BookJPA;



/**
 * Managed Bean ListCategoriesMBean
 */
@ManagedBean(name = "saleArticlesMBean")
@SessionScoped

public class SaleArticlesMBean {
	
	
	protected Collection<ArticlePrototypeJPA> articlesSearchView;
	
	public SaleArticlesMBean() throws Exception{
		
	}
	
	public Collection<ArticlePrototypeJPA> getArticlesSearchView() throws Exception	{		

		//CODIGO PARA EL PROTOTIPO:
		List<ArticlePrototypeJPA> articlesSearchView = new ArrayList<ArticlePrototypeJPA>();
		ArticlePrototypeJPA a1 = new ArticlePrototypeJPA("Camiseta espirales", 25.50);
		ArticlePrototypeJPA a2 = new ArticlePrototypeJPA("Poster espirales Man Ray", 99.50);
		ArticlePrototypeJPA a3 = new ArticlePrototypeJPA("Espirales taza", 14.99);
		articlesSearchView.add(a1);
		articlesSearchView.add(a2);
		articlesSearchView.add(a3);
		
		return articlesSearchView;
		
	}
}
