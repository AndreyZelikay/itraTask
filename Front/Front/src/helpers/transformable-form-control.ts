import { FormControl, ValidatorFn } from '@angular/forms';

export class TransformableFormControl extends FormControl {
    private transformCallback: Function | null = null;
    private formaterCallback: Function | null = null;

    constructor(
      formState?: any,
      validator?: ValidatorFn | ValidatorFn[] | null,
      transformCallback?: Function | null,
      formaterCallback?: Function | null
    ) {
        super(formState, validator, null);

        this.transformCallback = transformCallback;
        this.formaterCallback = formaterCallback;
    }

    public getValue() {
        if (this.transformCallback) {
            return this.transformCallback(this.value);
        }
        return this.value;
    }

    public setValue(value) {
        if (this.formaterCallback) {
            value = this.formaterCallback(value);
        }
        super.setValue(value);
    }
}