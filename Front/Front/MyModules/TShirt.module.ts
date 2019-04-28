import {CommentModule} from './Comment.module';

export interface TShirt {
	id: number;
	description: String;
	url: String;
	name: String;
	tags: String[];
	comments: CommentModule[];
	theme: string;
}
