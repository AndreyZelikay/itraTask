import {
  AsyncValidatorFn,
  FormGroup,
  ValidatorFn
} from '@angular/forms';
import { AbstractControlOptions } from '@angular/forms/src/model';

export class TransformableFormGroup extends FormGroup {
    private transformControlsCallback: Function | null = null;

    public controls: {
        [key: string]: any;
    };

    constructor(
        controls: {
            [ key: string ]: any;
        },
        transformControlsCallback?: Function | null,
        validatorOrOpts?: ValidatorFn | ValidatorFn[] | AbstractControlOptions | null) {
            super(controls, validatorOrOpts);
            this.transformControlsCallback = transformControlsCallback;
        }

    public getControls() {
        if (this.transformControlsCallback) {
            return this.transformControlsCallback({...this.controls});
        }

        return this.controls;
    }
}
