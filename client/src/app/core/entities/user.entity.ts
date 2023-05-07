import {Expose} from 'class-transformer';
import {InitializableEntity} from '@core/entities/extra/intializable-entity';


export class User extends InitializableEntity {
    @Expose() id?: number = 0;

    @Expose({name: 'name'}) name: string = '';

    @Expose({name: 'last_name'}) lastName: string = '';

    @Expose() email: string = '';

    constructor(values?: Partial<User>) {
        super();
        this.initEntity(values);
    }

}

export class RegisterUser extends User {
    @Expose() password: string = '';
}
