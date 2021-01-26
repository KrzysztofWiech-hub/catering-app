export interface ProductModel {
  name: string;
  description: string;
  cost: number;
  kcal: number;
  mass: number;
  height: number;
  weight: number;
}

export interface DaysModel {
  dayName?: string;
  productList?: ProductModel[];
}

export class DaysData implements DaysModel {
  constructor(public  dayName?: string,
              public productList?: ProductModel[]) {
  }
}
