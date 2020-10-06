namespace GithubEmojis
{
    public interface IGithubEmojiService
    {
        System.Threading.Tasks.Task<System.Collections.Generic.IList<Emoji>> GetEmojis();
        System.Collections.Generic.IList<Emoji> GetEmojisFrom(string content);
    }
}