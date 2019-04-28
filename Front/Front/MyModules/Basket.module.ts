import {TShirt} from './TShirt.module';

export interface BasketModule {
    products: {
        tShirt: TShirt,
        size: string,
        id: number
    }[];
}
