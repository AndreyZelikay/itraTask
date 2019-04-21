import { Validators } from '@angular/forms';
import { TransformableFormGroup, TransformableFormControl, CustomValidators } from '../../helpers';

export const form = new TransformableFormGroup({
    password: new TransformableFormControl(
        '',
        [
            CustomValidators.passwordValidator,
            Validators.required
        ]
    ),
    username: new TransformableFormControl(
        '',
        [
            Validators.required,

        ]
    )
});