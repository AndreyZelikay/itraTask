import {CommentModule} from './Comment.module';

export interface TShirt {
	id: Number;
	description: String;
	url: String;
	name: String;
	tags: String[];
	comments: CommentModule[];
}
