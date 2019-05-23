package main;

public class ScientificNotation {
		double coef;
		int exp;
		ScientificNotation(double coef,int exp)
		{
			this.coef=coef;
			this.exp=exp;
		}
		ScientificNotation()
		{
			this.coef=0;
			this.exp=0;
		}
		public ScientificNotation multiply(ScientificNotation b)
		{
			return this.fix(new ScientificNotation(this.coef*b.coef,this.exp+b.exp));
		}
		private ScientificNotation fix(ScientificNotation s) {
			while(s.coef>=10)
			{
				s.coef/=10.0;
				s.exp++;
			}
			while(s.coef<1)
			{
				s.coef*=10;
				s.exp--;
			}	
			return s;
		}
}
