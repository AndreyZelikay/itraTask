import { Pipe, PipeTransform } from '@angular/core';

@Pipe({name: 'array'})
export class ArrayPipe implements PipeTransform {
    public transform(value, args: any): any {
        const keys = [];
        for (const key in value) {
            keys.push({key: key, value: value[key]});
        }
        return keys;
    }
}
