import {LoginService} from 'MyServices/LogInService/login.service';

export class CustomValidators {

    public static cyrillic(control) {
        if (control.value.length > 0) {
            const reg = new RegExp(/^[\sА-Яа-яёЁЇїІіЄєҐґ']+$/);
            if (reg.test(control.value)) {
                return null;
            } else {
                return { invalid_cyrillic_name: true };
            }
        }
        return null;
    }
    public static passwordValidator(control){
        if(control.value.length>0){
            const reg= new RegExp(/^(?=.*\d)(?=.*[a-zA-Z])[a-zA-Z0-9]{8,}$/);
            if(reg.test(control.value)){
                return null;
            }
            else{
                return {invalid_password:true};
            }
        }
        return null;
    }
}
