import { Validators } from '@angular/forms';
import { TransformableFormGroup, TransformableFormControl } from '../../helpers';

export const form = new TransformableFormGroup({
    body: new TransformableFormControl(
        '',
        [
            Validators.required
        ]
    ),
    tShirtId: new TransformableFormControl(
        '',
        [
            Validators.required
        ]
    )
});
