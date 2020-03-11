#Source: https://xainey.github.io/2016/powershell-classes-and-concepts/
class CyberNinja
{
    # Properties
    [String] $Alias
    [int32] $HitPoints
    [String] $featuresAns

    # Static Properties
    static [String] $Clan = "DevOps Library"

    # Hidden Properties
    hidden [String] $RealName

    # Parameterless Constructor
    CyberNinja ()
    {
    }

    # Constructor
    CyberNinja ([String] $Alias, [int32] $HitPoints)
    {
        $this.Alias = $Alias
        $this.HitPoints = $HitPoints
        $this.featuresAns = "hi"
    }

    # Getters
    [String] getAlias()
    {
       return $this.Alias
    }

    # Getters
    [String] getFeaturesAns()
    {
       return $this.featuresAns
    }

    # Setters
    [void]setFeaturesAns([String] $featuresAns){
        $this.featuresAns = $featuresAns
    }

    # ToString Method
    [String] ToString()
    {
        return $this.Alias + ":" + $this.HitPoints
    }
}

# Using the class object
[CyberNinja] $Ken = [CyberNinja]::new("Ken", 28)
$Ken.getAlias()
$Ken.setFeaturesAns("Applied a setter.")
$Ken.getFeaturesAns()