import { Validators } from '@angular/forms';
import { TransformableFormGroup, TransformableFormControl, CustomValidators } from '../../helpers';

export const form = new TransformableFormGroup({
    email: new TransformableFormControl(
        '',
        [
            Validators.required,
            Validators.email,
        ]
    ),
});
